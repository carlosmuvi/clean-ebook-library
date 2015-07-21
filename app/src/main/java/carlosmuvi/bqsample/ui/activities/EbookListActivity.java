package carlosmuvi.bqsample.ui.activities;

import android.os.Bundle;

import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerEbookListComponent;
import carlosmuvi.bqsample.di.components.EbookListComponent;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;

public class EbookListActivity extends BaseActivity {

    private EbookListComponent ebookListComponent;

    public EbookListComponent component() {
        if (ebookListComponent == null) {
            ebookListComponent = DaggerEbookListComponent.builder()
                    .applicationComponent(((BqSampleApp) getApplication()).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return ebookListComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_ebook_list;
    }
}
