package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.model.Ebook;
import java.util.List;
import rx.Subscriber;

/**
 * Created by carlos.
 */
public interface ReorderEbooksUsecase {

  void execute(Subscriber subscriber, List<Ebook> ebooks, int orderBy);

  void unsubscribe();

}
