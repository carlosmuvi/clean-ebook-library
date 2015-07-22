package carlosmuvi.bqsample.ui.activities.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import carlosmuvi.bqsample.R;

/**
 * Created by carlos.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        ButterKnife.bind(this);


    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }
}
