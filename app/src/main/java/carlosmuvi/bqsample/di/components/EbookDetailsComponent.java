package carlosmuvi.bqsample.di.components;

import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.EbookDetailsModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.ui.activities.EbookDetailsActivity;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, EbookDetailsModule.class
})
public interface EbookDetailsComponent extends GenericActivityComponent {

    void inject(EbookDetailsActivity ebookDetailsActivity);

    //PRESENTER, USECASES, DATASOURCE
}
