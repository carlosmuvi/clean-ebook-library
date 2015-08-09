package carlosmuvi.data.dropbox.filter;

import com.dropbox.client2.DropboxAPI;
import rx.functions.Func1;

/**
 * Created by carlos.
 */
public class IsEpubFilter implements Func1<DropboxAPI.DeltaEntry<DropboxAPI.Entry>, Boolean> {

  public static final String EXT_EPUB = ".epub";

  @Override public Boolean call(DropboxAPI.DeltaEntry<DropboxAPI.Entry> entry) {
    return entry.metadata != null && !entry.metadata.isDir && entry.metadata.fileName()
        .substring(entry.metadata.fileName().length() - 5)
        .equalsIgnoreCase(EXT_EPUB);
  }
}
