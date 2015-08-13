package carlosmuvi.data.dropbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.data.R;
import carlosmuvi.data.dropbox.exception.DropboxBookException;
import carlosmuvi.data.dropbox.exception.DropboxLoginException;
import carlosmuvi.data.dropbox.filter.IsEpubFilter;
import carlosmuvi.data.dropbox.filter.NotNullFilter;
import carlosmuvi.data.dropbox.mapper.EbookDataMapper;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DeltaEntry;
import com.dropbox.client2.DropboxAPI.DeltaPage;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by carlos.
 */
public class DropboxDatasource implements EbookDatasource {

  public static final String DROPBOX_PREFKEY_NAME = "ACCESS_KEY_NAME";
  public static final String DROPBOX_PREFKEY_SECRET = "ACCESS_SECRET_NAME";

  private Activity activity;
  private DropboxAPI<AndroidAuthSession> dropboxAPI;

  @Inject public DropboxDatasource(Activity activity) {

    this.activity = activity;

    AndroidAuthSession session = buildSession();
    dropboxAPI = new DropboxAPI<>(session);
  }

  /**
   * Start Dropbox login
   */
  @Override public boolean startLogin() {
    dropboxAPI.getSession().startOAuth2Authentication(activity);
    return true;
  }

  @Override public Observable<String> completeLogin() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        if (dropboxAPI.getSession().authenticationSuccessful()) {
          try {
            dropboxAPI.getSession().finishAuthentication();
            storeAuth(dropboxAPI.getSession());
            subscriber.onCompleted();
          } catch (IllegalStateException e) {
            subscriber.onError(new DropboxLoginException());
          }
        } else {
          subscriber.onError(new DropboxLoginException());
        }
      }
    });
  }

  /**
   * Find all .epub files existing in current Dropbox Account
   */
  @Override public Observable<Ebook> listAllEbooks() {

    return Observable.create(new Observable.OnSubscribe<DeltaEntry<Entry>>() {
      @Override public void call(Subscriber<? super DeltaEntry<Entry>> subscriber) {

        // restore Dropbox authentication info
        if (dropboxAPI == null) {
          AndroidAuthSession session = buildSession();
          dropboxAPI = new DropboxAPI<>(session);
        }

        try {
          boolean hasMore = true;
          String cursor = null;

          while (hasMore) {
            DeltaPage<Entry> result = dropboxAPI.delta(cursor);
            cursor = result.cursor;
            hasMore = result.hasMore;

            for (DeltaEntry<Entry> entry : result.entries) {
              subscriber.onNext(entry);
            }
          }
          subscriber.onCompleted();
        } catch (DropboxException e) {
          Log.e("DEBUG", "error getting delta from dropbox");
          subscriber.onError(new DropboxBookException());
        }
      }
    })
        //remove not .epub files
        .filter(new IsEpubFilter())
        //transform dropbox files to ebook files
        .map(new EbookDataMapper(dropboxAPI))
        //remove null ebooks
        .filter(new NotNullFilter<Ebook>());
  }

  /**
   * Shows keeping the access keys returned from Trusted Authenticator in a local
   * store, rather than storing user name & password, and re-authenticating each
   * time (which is not to be done, ever).
   */
  private void loadAuth(AndroidAuthSession session) {
    SharedPreferences prefs = activity.getSharedPreferences("ACCOUNT_PREFS_NAME", 0);
    String key = prefs.getString(DROPBOX_PREFKEY_NAME, null);
    String secret = prefs.getString(DROPBOX_PREFKEY_SECRET, null);
    if (key == null || secret == null || key.length() == 0 || secret.length() == 0) return;

    if (key.equals("oauth2:")) {
      // If the key is set to "oauth2:", then we can assume the token is for OAuth 2.
      session.setOAuth2AccessToken(secret);
    } else {
      // Still support using old OAuth 1 tokens.
      session.setAccessTokenPair(new AccessTokenPair(key, secret));
    }
  }

  /**
   * Shows keeping the access keys returned from Trusted Authenticator in a local
   * store, rather than storing user name & password, and re-authenticating each
   * time (which is not to be done, ever).
   */
  private void storeAuth(AndroidAuthSession session) {
    // Store the OAuth 2 access token, if there is one.
    String oauth2AccessToken = session.getOAuth2AccessToken();
    if (oauth2AccessToken != null) {
      SharedPreferences prefs = activity.getSharedPreferences("ACCOUNT_PREFS_NAME", 0);
      SharedPreferences.Editor edit = prefs.edit();
      edit.putString(DROPBOX_PREFKEY_NAME, "oauth2:");
      edit.putString(DROPBOX_PREFKEY_SECRET, oauth2AccessToken);
      edit.commit();
      return;
    }
    // Store the OAuth 1 access token, if there is one.  This is only necessary if
    // you're still using OAuth 1.
    AccessTokenPair oauth1AccessToken = session.getAccessTokenPair();
    if (oauth1AccessToken != null) {
      SharedPreferences prefs = activity.getSharedPreferences("ACCOUNT_PREFS_NAME", 0);
      SharedPreferences.Editor edit = prefs.edit();
      edit.putString(DROPBOX_PREFKEY_NAME, oauth1AccessToken.key);
      edit.putString(DROPBOX_PREFKEY_SECRET, oauth1AccessToken.secret);
      edit.commit();
      return;
    }
  }

  /**
   * Build Dropbox Authentication Session that will be used when comunicating with Dropbox servers.
   *
   * @return Dropbox Auth Session
   */
  private AndroidAuthSession buildSession() {
    AppKeyPair appKeyPair = new AppKeyPair(activity.getString(R.string.dropbox_key),
        activity.getString(R.string.dropbox_secret));

    AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
    loadAuth(session);
    return session;
  }
}

