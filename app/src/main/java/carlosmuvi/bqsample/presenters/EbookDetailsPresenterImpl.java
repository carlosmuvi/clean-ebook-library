package carlosmuvi.bqsample.presenters;

import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.navigation.Navigator;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class EbookDetailsPresenterImpl implements EbookDetailsPresenter {

  Navigator navigator;
  View view;

  @Inject public EbookDetailsPresenterImpl(Navigator navigator) {
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

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */
  @Override public void onResume() {
    Ebook ebook = navigator.getEbookExtra();
    view.showBook(ebook);
  }

  @Override public void onPause() {

  }
  @Override public void onDestroy() {

  }
}
