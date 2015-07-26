package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.interactors.LoginUsecase;
import carlosmuvi.bqsample.interactors.LoginUsecaseImpl;
import carlosmuvi.bqsample.presenters.LoginPresenter;
import carlosmuvi.bqsample.presenters.LoginPresenterImpl;
import carlosmuvi.data.dropbox.DropboxDatasource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module public class LoginModule {

  //PRESENTER
  @Provides @ActivityScope LoginPresenter provideLoginPresenter(LoginPresenterImpl presenter) {
    return presenter;
  }

  //USECASES
  @Provides @ActivityScope LoginUsecase provideLoginUsecase(LoginUsecaseImpl loginUsecase) {
    return loginUsecase;
  }

  //DATASOURCE
  @Provides @ActivityScope EbookDatasource provideEbookDatasource(
      DropboxDatasource dropboxDatasource) {
    return dropboxDatasource;
  }
}
