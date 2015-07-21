package carlosmuvi.bqsample.ui.activities;

import android.os.Bundle;

import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerEbookDetailsComponent;
import carlosmuvi.bqsample.di.components.EbookDetailsComponent;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;

public class EbookDetailsActivity extends BaseActivity {

    private EbookDetailsComponent ebookDetailsComponent;

    public EbookDetailsComponent component() {
        if (ebookDetailsComponent == null) {
            ebookDetailsComponent = DaggerEbookDetailsComponent.builder()
                    .applicationComponent(((BqSampleApp) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return ebookDetailsComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_ebook_details;
    }
}
