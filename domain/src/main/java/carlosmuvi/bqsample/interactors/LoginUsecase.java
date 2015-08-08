package carlosmuvi.bqsample.interactors;

/**
 * Created by carlos.
 */
public interface LoginUsecase {

  void executeStartLogin();

  void executeEndLogin(DefaultSubscriber subscriber);
}
