package carlosmuvi.bqsample.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerEbookListComponent;
import carlosmuvi.bqsample.di.components.EbookListComponent;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.presenters.EbookListPresenter;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;

public class EbookListActivity extends BaseActivity implements EbookListPresenter.View {

    /**
     * *********************
     * Dependency injection
     * *********************
     */

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

    @Inject
    EbookListPresenter presenter;

    /**
     * *********************
     * Activity lifecycle
     * *********************
     */

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component().inject(this);

        presenter.setView(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ebook_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_showAs_list:
                break;
            case R.id.action_showAs_grid:
                break;
            case R.id.action_orderBy_title:
                break;
            case R.id.action_orderBy_date:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_ebook_list;
    }


    /**
     * *********************
     * View Inherited
     * *********************
     */


    @Override
    public void showEbooks(List<Ebook> ebooks) {

    }

    @Override
    public void reloadEbooks(List<Ebook> ebooks) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void updateLoading(String message) {

    }

    @Override
    public void switchView() {

    }

}
