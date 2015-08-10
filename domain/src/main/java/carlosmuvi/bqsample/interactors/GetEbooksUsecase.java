package carlosmuvi.bqsample.interactors;

import rx.Subscriber;

/**
 * Created by carlos.
 */
public interface GetEbooksUsecase {

  void execute(Subscriber subscriber);

  void unsubscribe();

}
