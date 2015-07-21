package carlosmuvi.bqsample.di.components;

import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.EbookListModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.ui.activities.EbookListActivity;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {
        ActivityModule.class, EbookListModule.class
})
public interface EbookListComponent extends GenericActivityComponent {

    void inject(EbookListActivity ebookListActivity);

    //PRESENTER, USECASES, DATASOURCE
}
