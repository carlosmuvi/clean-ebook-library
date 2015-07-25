package carlosmuvi.bqsample.presenters;

import android.util.Log;
import carlosmuvi.bqsample.interactors.GetEbooksUsecase;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecase;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.navigation.Navigator;
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

  @Override public void getEbooks() {

    final int[] proccessedEbooks = { 0 };

    view.showLoading();
    getEbooksUsecase.execute(new GetEbooksUsecase.Callback() {
      @Override public void onSuccess(List<Ebook> ebooks) {
        view.showEbooks(ebooks);
        view.hideLoading();
      }

      @Override public void onError() {
        view.hideLoading();
        Log.e("ERROR", "ERROR GETTING BOOKS");
      }

      @Override public void onEbookProcessed() {
        proccessedEbooks[0]++;
        view.updateLoading(proccessedEbooks[0] + " ebooks found...");
      }
    });
  }

  @Override public void reorderEbooks(List<Ebook> ebooks, int orderBy) {
    view.showLoading();
    reorderEbooksUsecase.execute(new ReorderEbooksUsecase.Callback() {
      @Override public void onSuccess(List<Ebook> ebooks) {
        view.reloadEbooks(ebooks);
        view.hideLoading();
      }

      @Override public void onError() {
        view.hideLoading();
        Log.e("ERROR", "ERROR ORDERING BOOKS");
      }
    }, ebooks, orderBy);
  }

  @Override public void setView(View view) {
    this.view = view;
  }

  @Override public void onEbookClick(Ebook ebook) {
    navigator.navigateToEbookDetails(ebook);
  }
}
