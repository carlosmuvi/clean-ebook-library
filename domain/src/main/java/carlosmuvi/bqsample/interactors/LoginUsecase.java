package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.executor.Interactor;

/**
 * Created by carlos.
 */
public interface LoginUsecase extends Interactor {

    interface Callback {
        void onLoginSuccess();

        void onError();
    }

    void executeStartLogin();

    void executeEndLogin(Callback callback);
}
