package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.model.Ebook;
import java.util.List;
import rx.Observable;

/**
 * Created by carlos.
 */
public interface ReorderEbooksUsecase {

  Observable execute(List<Ebook> ebooks, int orderBy);
}
