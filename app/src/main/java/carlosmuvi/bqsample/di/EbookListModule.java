package carlosmuvi.bqsample.di;

import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.presenters.EbookDetailsPresenter;
import carlosmuvi.bqsample.presenters.EbookListPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos.
 */
@Module
public class EbookListModule {

    //PRESENTER

    @Provides
    @ActivityScope
    EbookListPresenter provideEbookListPresenter(
            EbookListPresenter presenter) {
        return presenter;
    }

    //USECASES
}
