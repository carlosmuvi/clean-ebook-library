package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.model.Ebook;
import java.util.List;

/**
 * Created by carlos.
 */
public interface ReorderEbooksUsecase {

  void execute(DefaultSubscriber subscriber, List<Ebook> ebooks, int orderBy);

  void unsubscribe();

}
