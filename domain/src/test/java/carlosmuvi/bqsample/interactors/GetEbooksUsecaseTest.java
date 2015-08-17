package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

/**
 * Created by carlos.
 */
public class GetEbooksUsecaseTest {

  private GetEbooksUsecase useCase;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private MainThread mockPostExecutionThread;
  @Mock private EbookDatasource ebookDatasource;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.useCase =
        new GetEbooksUsecaseImpl(ebookDatasource, mockPostExecutionThread, mockThreadExecutor);
  }

  @Test @SuppressWarnings("unchecked") public void testGetEbooksUsecaseReturnCorrectResult() {

    int EBOOK_SIZE = 5;

    TestSubscriber<Ebook> testSubscriber = new TestSubscriber<>();
    TestScheduler testScheduler = new TestScheduler();
    given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);
    given(ebookDatasource.listAllEbooks()).willReturn(
        TestUtils.generateMockEbookObservable(EBOOK_SIZE));

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(EBOOK_SIZE));
  }
}
