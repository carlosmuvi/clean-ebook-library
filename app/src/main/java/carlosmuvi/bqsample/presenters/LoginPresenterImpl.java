package carlosmuvi.bqsample.presenters;

import android.util.Log;

import javax.inject.Inject;

import carlosmuvi.bqsample.interactors.LoginUsecase;

/**
 * Created by carlos.
 */
public class LoginPresenterImpl implements LoginPresenter {

    View view;
    LoginUsecase usecase;

    private boolean loginProccessStarted = false;

    @Inject
    public LoginPresenterImpl(LoginUsecase usecase) {
        this.usecase = usecase;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void login() {
        usecase.executeStartLogin();
        loginProccessStarted = true;
    }

    @Override
    public void onResume() {
        if (loginProccessStarted) {
            loginProccessStarted = false;
            view.showLoading();
            usecase.executeEndLogin(new LoginUsecase.Callback() {
                @Override
                public void onLoginSuccess() {
                    Log.e("DEBUG", "success end login!");
                    view.hideLoading();
                    //TODO Navigate to ebook list view
                }

                @Override
                public void onError() {
                    Log.e("DEBUG", "error end login!");
                    view.hideLoading();
                    view.showMessage("Error authenticating with Dropbox");
                }
            });
        }
    }

}
