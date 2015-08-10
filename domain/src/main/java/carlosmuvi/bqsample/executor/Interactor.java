package carlosmuvi.bqsample.executor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by carlos.
 */
public abstract class Interactor {

  private final ThreadExecutor threadExecutor;
  private final MainThread postExecutionThread;

  private Subscription subscription = Subscriptions.empty();

  protected Interactor(ThreadExecutor threadExecutor,
      MainThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  protected abstract Observable buildUseCaseObservable();

  @SuppressWarnings("unchecked")
  public void execute(Subscriber subscriber) {
    this.subscription = this.buildUseCaseObservable()
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler())
        .subscribe(subscriber);
  }

  /**
   * Unsubscribes from current {@link rx.Subscription}.
   */
  public void unsubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }
}