package carlosmuvi.bqsample.presenters;

/**
 * Created by carlos.
 */
public interface LoginPresenter extends Presenter {

  void setView(View view);

  void login();

  interface View {

    void showLoading();

    void hideLoading();

    void showMessage(String message);
  }
}
