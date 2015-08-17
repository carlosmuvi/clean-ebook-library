package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
        new ReorderEbooksUsecaseImpl(ebookDatasource, mockPostExecutionThread, mockThreadExecutor);
  }

  @Test @SuppressWarnings("unchecked") public void testReorderEbooksUsecaseReturnCorrectResult() {

    int EBOOK_SIZE = 5;

    TestSubscriber<Ebook> testSubscriber = new TestSubscriber<>();
    TestScheduler testScheduler = new TestScheduler();
    given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

    useCase.execute(testSubscriber, TestUtils.generateMockEbookList(EBOOK_SIZE),
        ReorderEbooksUsecaseImpl.ORDERBY_TITLE);

    assertThat(testSubscriber.getOnNextEvents().size(), is(EBOOK_SIZE));
  }
}
