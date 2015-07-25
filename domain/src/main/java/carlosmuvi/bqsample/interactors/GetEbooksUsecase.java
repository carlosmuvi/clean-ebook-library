package carlosmuvi.bqsample.interactors;

import carlosmuvi.bqsample.executor.Interactor;
import carlosmuvi.bqsample.model.Ebook;
import java.util.List;

/**
 * Created by carlos.
 */
public interface GetEbooksUsecase extends Interactor {

  interface Callback {

    void onSuccess(List<Ebook> ebooks);

    void onError();

    void onEbookProcessed();
  }

  void execute(Callback callback);
}
