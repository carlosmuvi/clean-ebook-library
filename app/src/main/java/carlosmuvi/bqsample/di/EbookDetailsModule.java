package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.presenters.EbookDetailsPresenter;
import carlosmuvi.bqsample.presenters.EbookDetailsPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module
public class EbookDetailsModule {

    //PRESENTER
    @Provides
    @ActivityScope
    EbookDetailsPresenter provideEbookDetailPresenter(
            EbookDetailsPresenterImpl presenter) {
        return presenter;
    }

    //USECASES
}
