package carlosmuvi.bqsample.di.components;

import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.LoginModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.ui.activities.LoginActivity;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, LoginModule.class
})
public interface LoginComponent extends GenericActivityComponent {

    //Main activity and game list fragments can get injected through this component.
    void inject(LoginActivity loginActivity);

    //LoginPresenter loginPresenter();

    //LoginUsecase loginUsecase();

    //EbookDatasource provideDataSource();
}
