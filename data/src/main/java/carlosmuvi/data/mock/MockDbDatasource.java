package carlosmuvi.data.mock;

import carlosmuvi.bqsample.datasource.EbookDatasource;
import carlosmuvi.bqsample.model.Ebook;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Mock Dropbox datasource used to test Drobpox requests.
 * <p/>
 * Created by carlos.
 */
public class MockDbDatasource implements EbookDatasource {

  @Inject public MockDbDatasource() {

  }

  @Override public void startLogin() {

  }

  @Override public Observable<String> completeLogin() {
    return Observable.just("OK");
  }

  @Override public Observable<Ebook> listAllEbooks() {
    List<Ebook> books = new ArrayList<>();
    for (int i = 5; i > 0; i--) {

      Ebook ebook = new Ebook();
      ebook.setTitle("Don Quijote de la Mancha, Parte " + i);
      ebook.setAuthor("Miguel de Cervantes Saavedra ");
      ebook.setPath("/very/very/long/path/in/dropbox ");
      ebook.setCreated(new Date(100000 * i));
      books.add(ebook);
    }

    return Observable.from(books);
  }
}
