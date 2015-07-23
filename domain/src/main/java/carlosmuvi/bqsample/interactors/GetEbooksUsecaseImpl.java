package carlosmuvi.bqsample.interactors;

import java.util.List;

import javax.inject.Inject;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public class GetEbooksUsecaseImpl implements GetEbooksUsecase {

    EbookDatasource ebookDatasource;
    MainThread mainThread;
    ThreadExecutor threadExecutor;

    Callback callback;

    @Inject
    public GetEbooksUsecaseImpl(EbookDatasource ebookDatasource,
                                MainThread mainThread,
                                ThreadExecutor threadExecutor) {

        this.ebookDatasource = ebookDatasource;
        this.mainThread = mainThread;
        this.threadExecutor = threadExecutor;

    }

    @Override
    public void run() {
        this.ebookDatasource.listAllEbooks(new EbookDatasource.EbookListCallback() {
            @Override
            public void onNext() {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onEbookProcessed();
                    }
                });
            }

            @Override
            public void onSuccess(final List<Ebook> books) {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(books);
                    }
                });
            }

            @Override
            public void onError() {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError();
                    }
                });
            }
        });
    }

    @Override
    public void execute(GetEbooksUsecase.Callback callback) {
        this.callback = callback;
        threadExecutor.run(this);
    }

}
