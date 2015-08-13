package carlosmuvi.bqsample.datasource;

import carlosmuvi.bqsample.model.Ebook;
import rx.Observable;

/**
 * Created by carlos.
 */
public interface EbookDatasource {

  boolean startLogin();

  Observable<String> completeLogin();

  Observable<Ebook> listAllEbooks();

}
