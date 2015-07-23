package carlosmuvi.bqsample.presenters;

import java.util.List;

import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public interface EbookListPresenter {

    void getEbooks();

    void reorderEbooks(List<Ebook> ebooks, int orderBy);

    void setView(View view);

    void onEbookClick(Ebook ebook);

    public interface View {

        void showEbooks(List<Ebook> ebooks);

        void reloadEbooks(List<Ebook> ebooks);

        void showLoading();

        void hideLoading();

        void updateLoading(String message);

        void switchView(int viewType);
    }
}
