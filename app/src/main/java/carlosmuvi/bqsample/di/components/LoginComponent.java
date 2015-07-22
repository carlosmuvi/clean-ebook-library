package carlosmuvi.bqsample.di.components;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.LoginModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.interactors.LoginUsecase;
import carlosmuvi.bqsample.presenters.LoginPresenter;
import carlosmuvi.bqsample.ui.activities.LoginActivity;
import carlosmuvi.data.dropbox.mapper.DropboxBookMapper;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LoginModule.class
})
public interface LoginComponent extends GenericActivityComponent {

    void inject(LoginActivity loginActivity);

    LoginPresenter getloginPresenter();

    LoginUsecase getloginUsecase();

    EbookDatasource getDatasource();

    DropboxBookMapper getMapper();

}
