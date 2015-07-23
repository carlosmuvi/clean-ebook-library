package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.interactors.GetEbooksUsecase;
import carlosmuvi.bqsample.interactors.GetEbooksUsecaseImpl;
import carlosmuvi.bqsample.interactors.LoginUsecase;
import carlosmuvi.bqsample.interactors.LoginUsecaseImpl;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecase;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecaseImpl;
import carlosmuvi.bqsample.presenters.EbookListPresenter;
import carlosmuvi.bqsample.presenters.EbookListPresenterImpl;
import carlosmuvi.bqsample.presenters.LoginPresenter;
import carlosmuvi.bqsample.presenters.LoginPresenterImpl;
import carlosmuvi.data.dropbox.DropboxDatasource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module
public class EbookListModule {

    //PRESENTER
    @Provides
    EbookListPresenter provideEbookListPresenter(EbookListPresenterImpl presenter) {
        return presenter;
    }

    //USECASES
    @Provides
    GetEbooksUsecase provideEbooksUsecase(GetEbooksUsecaseImpl usecase) {
        return usecase;
    }

    @Provides
    ReorderEbooksUsecase provideReorderUsecase(ReorderEbooksUsecaseImpl usecase) {
        return usecase;
    }

    //DATASOURCE AND MAPPER
    @Provides
    EbookDatasource provideEbookDatasource(DropboxDatasource dropboxDatasource) {
        return dropboxDatasource;
    }
}
