package carlosmuvi.bqsample.presenters;

/**
 * Created by carlos.
 */
public interface LoginPresenter {

    void setView(View view);

    void login();

    void onResume();

    interface View {

        void showLoading();

        void hideLoading();

        void showMessage(String message);

    }
}
