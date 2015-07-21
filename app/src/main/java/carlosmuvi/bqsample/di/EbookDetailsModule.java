package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.presenters.EbookDetailsPresenter;
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
            EbookDetailsPresenter presenter) {
        return presenter;
    }

    //USECASES
}
