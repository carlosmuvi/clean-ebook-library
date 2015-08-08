package carlosmuvi.bqsample.interactors;

import rx.Observable;

/**
 * Created by carlos.
 */
public interface LoginUsecase {

  void executeStartLogin();

  Observable executeEndLogin();
}
