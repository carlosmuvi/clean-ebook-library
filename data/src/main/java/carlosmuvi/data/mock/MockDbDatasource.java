package carlosmuvi.data.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.model.Ebook;

/**
 * Mock Dropbox datasource used to test Drobpox requests.
 * <p/>
 * Created by carlos.
 */
public class MockDbDatasource implements EbookDatasource {

    @Inject
    public MockDbDatasource() {

    }

    @Override
    public void startLogin() {

    }

    @Override
    public void completeLogin(Callback callback) {
        callback.onSuccess();
    }

    @Override
    public void listAllEbooks(EbookListCallback callback) {
        List<Ebook> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            Ebook ebook = new Ebook();
            ebook.setTitle("TITLE " + i);
            ebook.setAuthor("AUTHOR " + i);
            ebook.setPath("PATH " + i);
            ebook.setCreated(new Date());
            books.add(ebook);
            callback.onNext();
        }

        callback.onSuccess(books);
    }
}
