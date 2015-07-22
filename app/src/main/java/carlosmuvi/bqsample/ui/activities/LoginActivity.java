package carlosmuvi.bqsample.ui.activities;

import android.os.Bundle;

import javax.inject.Inject;

import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerLoginComponent;
import carlosmuvi.bqsample.di.components.LoginComponent;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;


public class LoginActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

}
