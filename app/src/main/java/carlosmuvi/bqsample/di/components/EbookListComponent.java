package carlosmuvi.bqsample.di.components;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.EbookListModule;
import carlosmuvi.bqsample.di.scopes.ActivityScope;
import carlosmuvi.bqsample.interactors.GetEbooksUsecase;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecase;
import carlosmuvi.bqsample.presenters.EbookListPresenter;
import carlosmuvi.bqsample.ui.activities.EbookListActivity;
import dagger.Component;

/**
 * Created by carlos.
 */
@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, EbookListModule.class
}) public interface EbookListComponent extends GenericActivityComponent {

  void inject(EbookListActivity ebookListActivity);

  GetEbooksUsecase getEbooksUsecase();

  ReorderEbooksUsecase reorderEbooksUsecase();

  EbookListPresenter ebookListPresenter();

  EbookDatasource ebookDatasource();
}
