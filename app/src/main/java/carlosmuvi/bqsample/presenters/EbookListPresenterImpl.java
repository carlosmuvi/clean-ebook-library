package carlosmuvi.bqsample.presenters;

import carlosmuvi.bqsample.interactors.DefaultSubscriber;
import carlosmuvi.bqsample.interactors.GetEbooksUsecase;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecase;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.navigation.Navigator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class EbookListPresenterImpl implements EbookListPresenter {

  View view;
  GetEbooksUsecase getEbooksUsecase;
  ReorderEbooksUsecase reorderEbooksUsecase;
  Navigator navigator;

  @Inject public EbookListPresenterImpl(GetEbooksUsecase getEbooksUsecase,
      ReorderEbooksUsecase reorderEbooksUsecase, Navigator navigator) {
    this.getEbooksUsecase = getEbooksUsecase;
    this.reorderEbooksUsecase = reorderEbooksUsecase;
    this.navigator = navigator;
  }

  /**
   * *********************
   * Presenter inherited
   * *********************
   */

  @Override public void setView(View view) {
    this.view = view;
  }

  @Override public void getEbooks() {
    view.showLoading();
    getEbooksUsecase.execute(new EbookListSubscriber());
  }

  @Override public void reorderEbooks(List<Ebook> ebooks, int orderBy) {
    view.showLoading();
    reorderEbooksUsecase.execute(new EbookReorderSubscriber(), ebooks, orderBy);
  }

  @Override public void onEbookClick(Ebook ebook) {
    navigator.navigateToEbookDetails(ebook);
  }

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
    getEbooksUsecase.unsubscribe();
    reorderEbooksUsecase.unsubscribe();
  }

  /**
   * *********************
   * Subscribers
   * *********************
   */

  private final class EbookListSubscriber extends DefaultSubscriber<Ebook> {

    List<Ebook> ebooks = new ArrayList<>();

    @Override public void onCompleted() {
      view.showEbooks(ebooks);
      view.hideLoading();
    }

    @Override public void onError(Throwable e) {
      view.hideLoading();
      view.showMessage("error loading books!");
    }

    @Override public void onNext(Ebook ebook) {
      ebooks.add(ebook);
      view.updateLoading(ebooks.size() + " ebooks found...");
    }
  }

  private final class EbookReorderSubscriber extends DefaultSubscriber<List<Ebook>> {

    @Override public void onCompleted() {
    }

    @Override public void onError(Throwable e) {
      view.hideLoading();
      view.showMessage("error sorting books!");
    }

    @Override public void onNext(List<Ebook> ebooks) {
      view.reloadEbooks(ebooks);
      view.hideLoading();
    }
  }
}
