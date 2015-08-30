package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.model.Ebook;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rx.Observable;

/**
 * Created by carlos.
 */
public class TestUtils {

  public static List<Ebook> generateMockEbookList(int size) {

    ArrayList<Ebook> ebooks = new ArrayList<>();

    for (int i = size; i > 0; i--) {

      Ebook ebook = new Ebook();
      ebook.setTitle("" + (int)(Math.random() * 1000000));
      ebook.setAuthor("Miguel de Cervantes Saavedra ");
      ebook.setPath("/very/very/long/path/in/dropbox ");
      ebook.setCreated(new Date((long) (Math.random() * 100000000)));
      ebooks.add(ebook);
    }

    return ebooks;
  }

  public static Observable<Ebook> generateMockEbookObservable(int size) {

    return Observable.from(generateMockEbookList(size));
  }
}
