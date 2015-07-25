package carlosmuvi.bqsample.navigation;

import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public interface Navigator {
  void navigateToEbookList();

  void navigateToEbookDetails(Ebook ebook);

  Ebook getEbookExtra();
}
