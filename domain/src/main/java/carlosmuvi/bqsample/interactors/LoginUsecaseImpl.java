package carlosmuvi.bqsample.interactors;

/**
 * Created by carlos.
 */

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.Interactor;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by carlos.
 */
public class LoginUsecaseImpl extends Interactor implements LoginUsecase {

  EbookDatasource ebookDatasource;

  @Inject public LoginUsecaseImpl(EbookDatasource ebookDatasource, MainThread mainThread,
      ThreadExecutor threadExecutor) {

    super(threadExecutor, mainThread);
    this.ebookDatasource = ebookDatasource;

  }

  @Override public void executeStartLogin() {
    this.ebookDatasource.startLogin();
  }

  @Override public Observable executeEndLogin() {
    return buildUseCaseObservable();
  }

  @Override protected Observable buildUseCaseObservable() {
    return this.ebookDatasource.completeLogin();
  }
}
