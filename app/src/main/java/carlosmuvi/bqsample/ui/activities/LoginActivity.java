package carlosmuvi.bqsample.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import butterknife.OnClick;
import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerLoginComponent;
import carlosmuvi.bqsample.di.components.LoginComponent;
import carlosmuvi.bqsample.presenters.LoginPresenter;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

  /**
   * *********************
   * Dependency injection
   * *********************
   */

  private LoginComponent loginComponent;

  public LoginComponent component() {

    if (loginComponent == null) {
      loginComponent = DaggerLoginComponent.builder()
          .applicationComponent(((BqSampleApp) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return loginComponent;
  }

  @Inject LoginPresenter presenter;

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */

  ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    component().inject(this);

    presenter.setView(this);
  }

  @OnClick(R.id.loginBtn) public void onLoginButtonClick() {
    presenter.login();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_login;
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  /**
   * *********************
   * View Inherited
   * *********************
   */

  @Override public void showLoading() {
    progressDialog =
        ProgressDialog.show(this, getString(R.string.logging_in), getString(R.string.please_wait),
            true);
  }

  @Override public void hideLoading() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }

  @Override public void showMessage(String message) {
    Snackbar.make(this.getWindow().getDecorView(), message, Snackbar.LENGTH_LONG).show();
  }
}
