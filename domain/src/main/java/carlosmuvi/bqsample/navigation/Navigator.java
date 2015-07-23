package carlosmuvi.bqsample.navigation;

import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public interface Navigator {
    public void navigateToEbookList();

    public void navigateToEbookDetails(Ebook ebook);

    public Ebook getEbookExtra();
}
