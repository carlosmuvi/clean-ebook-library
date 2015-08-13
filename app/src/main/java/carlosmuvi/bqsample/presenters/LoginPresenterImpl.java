package carlosmuvi.bqsample.presenters;

import carlosmuvi.bqsample.interactors.DefaultSubscriber;
import carlosmuvi.bqsample.interactors.LoginUsecase;
import carlosmuvi.bqsample.navigation.Navigator;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class LoginPresenterImpl implements LoginPresenter {

  View view;
  LoginUsecase usecase;
  Navigator navigator;

  private boolean loginProccessStarted = false;

  @Inject public LoginPresenterImpl(LoginUsecase usecase, Navigator navigator) {
    this.usecase = usecase;
    this.navigator = navigator;
  }

  /**
   * *********************
   * Presenter inherited
   * *********************
   */

  @Override public void setView(View view) {
    this.view = view;
  }

  @Override public void login() {
    loginProccessStarted = true;
    if (!usecase.executeStartLogin()) {
      completeLogin();
    }
  }

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */
  @Override public void onResume() {
    completeLogin();
  }

  private void completeLogin() {
    if (loginProccessStarted) {
      loginProccessStarted = false;
      view.showLoading();
      usecase.executeEndLogin(new LoginSubscriber());
    }
  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
    usecase.unsubscribe();
  }

  /**
   * *********************
   * Subscribers
   * *********************
   */

  private final class LoginSubscriber extends DefaultSubscriber<String> {

    @Override public void onCompleted() {
      view.hideLoading();
      navigator.navigateToEbookList();
    }

    @Override public void onError(Throwable e) {
      view.hideLoading();
      view.showMessage("Error authenticating with Dropbox");
    }

    @Override public void onNext(String s) {
    }
  }
}
