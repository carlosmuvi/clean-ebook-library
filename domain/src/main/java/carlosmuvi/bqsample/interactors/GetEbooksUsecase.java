package carlosmuvi.bqsample.interactors;

/**
 * Created by carlos.
 */
public interface GetEbooksUsecase {

  void execute(DefaultSubscriber defaultSubscriber);

  void unsubscribe();

}
