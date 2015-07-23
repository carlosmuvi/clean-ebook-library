package carlosmuvi.bqsample.interactors;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.executor.MainThread;
import carlosmuvi.bqsample.executor.ThreadExecutor;
import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public class ReorderEbooksUsecaseImpl implements ReorderEbooksUsecase {

    EbookDatasource ebookDatasource;
    MainThread mainThread;
    ThreadExecutor threadExecutor;

    Callback callback;

    List<Ebook> ebooks;
    int orderBy;

    public static final int ORDERBY_DATE = 0;
    public static final int ORDERBY_TITLE = 1;

    @Inject
    public ReorderEbooksUsecaseImpl(EbookDatasource ebookDatasource,
                                    MainThread mainThread,
                                    ThreadExecutor threadExecutor) {

        this.ebookDatasource = ebookDatasource;
        this.mainThread = mainThread;
        this.threadExecutor = threadExecutor;

    }

    @Override
    public void run() {
        Collections.sort(ebooks, new Comparator<Ebook>() {
            @Override
            public int compare(Ebook ebook, Ebook t1) {
                if (orderBy == ORDERBY_TITLE) {
                    return ebook.getTitle().compareToIgnoreCase(t1.getTitle());
                } else {
                    return ebook.getCreated().compareTo(t1.getCreated());
                }
            }
        });
        notifyPresenter();
    }

    private void notifyPresenter() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(ebooks);
            }
        });
    }

    @Override
    public void execute(Callback callback, List<Ebook> ebooks, int orderBy) {
        this.callback = callback;
        this.ebooks = ebooks;
        this.orderBy = orderBy;

        threadExecutor.run(this);
    }


}
