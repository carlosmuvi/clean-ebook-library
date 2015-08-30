package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;
import rx.observers.TestSubscriber;

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
    this.useCase = new GetEbooksUsecaseImplTestClass(ebookDatasource, mockPostExecutionThread,
        mockThreadExecutor);
  }

  @Test @SuppressWarnings("unchecked") public void test_get_ebooks_usecase_returns_list_books() {

    int EBOOK_SIZE = 5;

    TestSubscriber<Ebook> testSubscriber = new TestSubscriber<>();
    given(ebookDatasource.listAllEbooks()).willReturn(
        TestUtils.generateMockEbookObservable(EBOOK_SIZE));

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(EBOOK_SIZE));
    testSubscriber.assertCompleted();
  }

  @Test @SuppressWarnings("unchecked") public void test_get_ebooks_usecase_returns_empty() {

    int EBOOK_SIZE = 0;

    TestSubscriber<Ebook> testSubscriber = new TestSubscriber<>();
    given(ebookDatasource.listAllEbooks()).willReturn(
        TestUtils.generateMockEbookObservable(EBOOK_SIZE));

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(EBOOK_SIZE));
    testSubscriber.assertCompleted();
  }

  /**
   * Modified Usecase class without specific rx android scheduling observeOn() and
   * subscribeOn() methods.
   */
  private class GetEbooksUsecaseImplTestClass extends GetEbooksUsecaseImpl {

    public GetEbooksUsecaseImplTestClass(EbookDatasource ebookDatasource, MainThread mainThread,
        ThreadExecutor threadExecutor) {
      super(ebookDatasource, mainThread, threadExecutor);
    }

    @Override public void execute(Subscriber subscriber) {
      super.buildUseCaseObservable().subscribe(subscriber);
    }
  }
}
