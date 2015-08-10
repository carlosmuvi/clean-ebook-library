package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.Interactor;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by carlos.
 */
public class ReorderEbooksUsecaseImpl extends Interactor implements ReorderEbooksUsecase {

  EbookDatasource ebookDatasource;

  List<Ebook> ebooks;
  int orderBy;

  public static final int ORDERBY_DATE = 0;
  public static final int ORDERBY_TITLE = 1;

  @Inject public ReorderEbooksUsecaseImpl(EbookDatasource ebookDatasource, MainThread mainThread,
      ThreadExecutor threadExecutor) {
    super(threadExecutor, mainThread);
    this.ebookDatasource = ebookDatasource;
  }

  @Override public void execute(Subscriber subscriber, List<Ebook> ebooks, int orderBy) {
    this.ebooks = ebooks;
    this.orderBy = orderBy;

    this.execute(subscriber);
  }

  @Override protected Observable buildUseCaseObservable() {
    return Observable.create(new Observable.OnSubscribe<List<Ebook>>() {
      @Override public void call(Subscriber<? super List<Ebook>> subscriber) {
        Collections.sort(ebooks, new Comparator<Ebook>() {
          @Override public int compare(Ebook ebook, Ebook t1) {
            if (orderBy == ORDERBY_TITLE) {
              return ebook.getTitle().compareToIgnoreCase(t1.getTitle());
            } else {
              return ebook.getCreated().compareTo(t1.getCreated());
            }
          }
        });
        subscriber.onNext(ebooks);
        subscriber.onCompleted();
      }
    });
  }

  @Override public void unsubscribe() {
    super.unsubscribe();
  }
}
