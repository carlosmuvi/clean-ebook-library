package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.Interactor;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by carlos.
 */
public class GetEbooksUsecaseImpl extends Interactor implements GetEbooksUsecase {

  EbookDatasource ebookDatasource;

  @Inject public GetEbooksUsecaseImpl(EbookDatasource ebookDatasource, MainThread mainThread,
      ThreadExecutor threadExecutor) {

    super(threadExecutor, mainThread);
    this.ebookDatasource = ebookDatasource;
  }

  @Override public void execute(DefaultSubscriber defaultSubscriber) {
    super.execute(defaultSubscriber);
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.ebookDatasource.listAllEbooks();
  }

  @Override public void unsubscribe() {
    super.unsubscribe();
  }
}
