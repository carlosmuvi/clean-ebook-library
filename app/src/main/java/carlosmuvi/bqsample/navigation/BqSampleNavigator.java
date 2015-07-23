package carlosmuvi.bqsample.navigation;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.ui.activities.EbookDetailsActivity;
import carlosmuvi.bqsample.ui.activities.EbookListActivity;

/**
 * Created by carlos.
 */
public class BqSampleNavigator implements Navigator {

    Activity activity;

    @Inject
    public BqSampleNavigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateToEbookList() {
        Intent intent = new Intent(activity, EbookListActivity.class);
        activity.startActivity(intent);
    }

    public void navigateToEbookDetails(Ebook ebook) {
        Intent intent = new Intent(activity, EbookDetailsActivity.class);
        intent.putExtra("ebook", ebook);
        activity.startActivity(intent);
    }

    public Ebook getEbookExtra() {
        return (Ebook) activity.getIntent().getSerializableExtra("ebook");
    }
}
