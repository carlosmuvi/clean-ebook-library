package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.executor.Interactor;
import carlosmuvi.bqsample.model.Ebook;
import java.util.List;

/**
 * Created by carlos.
 */
public interface ReorderEbooksUsecase extends Interactor {

  interface Callback {
    void onSuccess(List<Ebook> ebooks);

    void onError();
  }

  void execute(Callback callback, List<Ebook> ebooks, int orderBy);
}
