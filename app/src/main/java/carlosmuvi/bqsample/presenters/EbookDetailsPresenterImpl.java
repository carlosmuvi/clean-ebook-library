package carlosmuvi.bqsample.presenters;

import javax.inject.Inject;

import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.navigation.Navigator;

/**
 * Created by carlos.
 */
public class EbookDetailsPresenterImpl implements EbookDetailsPresenter {

    Navigator navigator;
    View view;

    @Inject
    public EbookDetailsPresenterImpl(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void onResume() {
        Ebook ebook = navigator.getEbookExtra();
        view.showBook(ebook);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
