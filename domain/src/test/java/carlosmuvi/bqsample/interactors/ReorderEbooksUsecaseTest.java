package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

/**
 * Created by carlos.
 */
public class ReorderEbooksUsecaseTest {

  private ReorderEbooksUsecase useCase;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private MainThread mockPostExecutionThread;
  @Mock private EbookDatasource ebookDatasource;

  @Before public void setUp() {

    this.useCase =
        new ReorderEbooksUsecaseImplTestClass(ebookDatasource, mockPostExecutionThread, mockThreadExecutor);
  }

  @Test @SuppressWarnings("unchecked") public void testReorderByTitleReturnCorrectResult() {

    int EBOOK_SIZE = 20;

    TestSubscriber<List<Ebook>> testSubscriber = new TestSubscriber<>();
    Ordering<Ebook> orderingByTitle = new OrderingByTitle();

    useCase.execute(testSubscriber, TestUtils.generateMockEbookList(EBOOK_SIZE),
        ReorderEbooksUsecaseImpl.ORDERBY_TITLE);

    List<Ebook> ebooks = testSubscriber.getOnNextEvents().get(0);

    testSubscriber.assertValueCount(1);
    testSubscriber.assertCompleted();
    assertThat(orderingByTitle.isOrdered(ebooks), is(true));

    assertThat(ebooks.size(), is(EBOOK_SIZE));

  }

  @Test @SuppressWarnings("unchecked") public void testReorderByDateReturnCorrectResult() {

    int EBOOK_SIZE = 20;

    TestSubscriber<List<Ebook>> testSubscriber = new TestSubscriber<>();
    Ordering<Ebook> orderingByDate = new OrderingByDate();

    useCase.execute(testSubscriber, TestUtils.generateMockEbookList(EBOOK_SIZE),
        ReorderEbooksUsecaseImpl.ORDERBY_DATE);

    List<Ebook> ebooks = testSubscriber.getOnNextEvents().get(0);

    testSubscriber.assertValueCount(1);
    testSubscriber.assertCompleted();
    assertThat(orderingByDate.isOrdered(ebooks), is(true));

    assertThat(ebooks.size(), is(EBOOK_SIZE));

  }

  private class OrderingByDate extends Ordering<Ebook> {
    @Override public int compare(Ebook left, Ebook right) {
      return Ordering.natural().compare(left.getCreated(), right.getCreated());
    }
  }

  private class OrderingByTitle extends Ordering<Ebook> {
    @Override public int compare(Ebook left, Ebook right) {
      return Ordering.natural().compare(left.getAuthor(), right.getAuthor());
    }
  }

  /**
   * Modified Usecase class without specific rx android scheduling observeOn() and
   * subscribeOn() methods.
   */
  private class ReorderEbooksUsecaseImplTestClass extends ReorderEbooksUsecaseImpl {

    public ReorderEbooksUsecaseImplTestClass(EbookDatasource ebookDatasource, MainThread mainThread,
        ThreadExecutor threadExecutor) {
      super(ebookDatasource, mainThread, threadExecutor);
    }

    @Override public void execute(Subscriber subscriber) {
      super.buildUseCaseObservable().subscribe(subscriber);
    }
  }
}
