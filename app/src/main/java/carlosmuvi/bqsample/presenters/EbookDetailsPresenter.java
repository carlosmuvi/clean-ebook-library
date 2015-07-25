package carlosmuvi.bqsample.presenters;

import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public interface EbookDetailsPresenter {

    void onResume();

    void setView(View view);

    interface View {

        void showBook(Ebook ebook);
    }
}
