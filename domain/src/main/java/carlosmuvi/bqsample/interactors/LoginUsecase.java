package carlosmuvi.bqsample.interactors;

import rx.Subscriber;

/**
 * Created by carlos.
 */
public interface LoginUsecase {

  void executeStartLogin();

  void executeEndLogin(Subscriber subscriber);

  void unsubscribe();
}
