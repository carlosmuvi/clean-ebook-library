package carlosmuvi.bqsample.navigation;

import android.app.Activity;
import android.content.Intent;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.ui.activities.EbookDetailsActivity;
import carlosmuvi.bqsample.ui.activities.EbookListActivity;
import javax.inject.Inject;

/**
 * Created by carlos.
 */
public class BqSampleNavigator implements Navigator {

  public static final String INTENT_KEY_EBOOK = "ebook";
  Activity activity;

  @Inject public BqSampleNavigator(Activity activity) {
    this.activity = activity;
  }

  public void navigateToEbookList() {
    Intent intent = new Intent(activity, EbookListActivity.class);
    activity.startActivity(intent);
  }

  public void navigateToEbookDetails(Ebook ebook) {
    Intent intent = new Intent(activity, EbookDetailsActivity.class);
    intent.putExtra(INTENT_KEY_EBOOK, ebook);
    activity.startActivity(intent);
  }

  public Ebook getEbookExtra() {
    return (Ebook) activity.getIntent().getSerializableExtra(INTENT_KEY_EBOOK);
  }
}
