package carlosmuvi.bqsample.presenters;

import android.util.Log;
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

  @Override public void setView(View view) {
    this.view = view;
  }

  @Override public void login() {
    usecase.executeStartLogin();
    loginProccessStarted = true;
  }

  @Override public void onResume() {
    if (loginProccessStarted) {
      loginProccessStarted = false;
      view.showLoading();
      usecase.executeEndLogin(new LoginUsecase.Callback() {
        @Override public void onLoginSuccess() {
          view.hideLoading();
          navigator.navigateToEbookList();
        }

        @Override public void onError() {
          view.hideLoading();
          view.showMessage("Error authenticating with Dropbox");
        }
      });
    }
  }
}
