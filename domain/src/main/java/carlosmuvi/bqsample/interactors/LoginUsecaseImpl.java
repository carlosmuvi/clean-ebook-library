package carlosmuvi.bqsample.interactors;

/**
 * Created by carlos.
 */

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class LoginUsecaseImpl implements LoginUsecase {

  EbookDatasource ebookDatasource;
  MainThread mainThread;
  ThreadExecutor threadExecutor;

  Callback callback;

  @Inject public LoginUsecaseImpl(EbookDatasource ebookDatasource, MainThread mainThread,
      ThreadExecutor threadExecutor) {

    this.ebookDatasource = ebookDatasource;
    this.mainThread = mainThread;
    this.threadExecutor = threadExecutor;
  }

  @Override public void run() {
    this.ebookDatasource.completeLogin(new EbookDatasource.Callback() {
      @Override public void onSuccess() {
        mainThread.post(new Runnable() {
          @Override public void run() {
            callback.onLoginSuccess();
          }
        });
      }

      @Override public void onError() {
        mainThread.post(new Runnable() {
          @Override public void run() {
            callback.onError();
          }
        });
      }
    });
  }

  @Override public void executeStartLogin() {
    this.ebookDatasource.startLogin();
  }

  @Override public void executeEndLogin(final Callback callback) {
    this.callback = callback;
    this.threadExecutor.run(this);
  }
}
