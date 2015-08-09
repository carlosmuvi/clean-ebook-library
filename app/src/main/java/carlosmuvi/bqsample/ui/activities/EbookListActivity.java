package carlosmuvi.bqsample.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerEbookListComponent;
import carlosmuvi.bqsample.di.components.EbookListComponent;
import carlosmuvi.bqsample.interactors.ReorderEbooksUsecaseImpl;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.presenters.EbookListPresenter;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;
import carlosmuvi.bqsample.ui.adapters.EbookAdapter;
import carlosmuvi.bqsample.ui.adapters.OnRecyclerViewItemClickListener;
import carlosmuvi.bqsample.ui.customviews.EbookRecyclerView;
import java.util.List;
import javax.inject.Inject;

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

  @Inject EbookListPresenter presenter;

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */

  private static final int VIEWTYPE_LIST = 10;
  private static final int VIEWTYPE_GRID = 11;

  @Bind(R.id.ebook_list_rv) public EbookRecyclerView recyclerView;
  @Bind(R.id.empty_list_tv) public TextView emptyListTv;

  ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    component().inject(this);

    presenter.setView(this);

    presenter.getEbooks();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_ebook_list, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_showAs_list:
        switchView(VIEWTYPE_LIST);
        break;
      case R.id.action_showAs_grid:
        switchView(VIEWTYPE_GRID);
        break;
      case R.id.action_orderBy_title:
        presenter.reorderEbooks(((EbookAdapter) recyclerView.getAdapter()).getItems(),
            ReorderEbooksUsecaseImpl.ORDERBY_TITLE);
        break;
      case R.id.action_orderBy_date:
        presenter.reorderEbooks(((EbookAdapter) recyclerView.getAdapter()).getItems(),
            ReorderEbooksUsecaseImpl.ORDERBY_DATE);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_ebook_list;
  }

  /**
   * *********************
   * View Inherited
   * *********************
   */

  @Override public void showEbooks(List<Ebook> ebooks) {
    final EbookAdapter adapter;
    recyclerView.setEmptyView(emptyListTv);
    recyclerView.setAdapter(adapter = new EbookAdapter(ebooks, R.layout.view_ebook_list));
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Ebook>() {
      @Override public void onItemClick(View view, Ebook ebook) {
        presenter.onEbookClick(ebook);
      }
    });
  }

  @Override public void reloadEbooks(List<Ebook> ebooks) {
    ((EbookAdapter) recyclerView.getAdapter()).setEbooks(ebooks);
  }

  @Override public void showLoading() {
    progressDialog = ProgressDialog.show(this, getString(R.string.loading_ebooks),
        getString(R.string.please_wait), true);
  }

  @Override public void updateLoading(String message) {
    progressDialog.setMessage(message);
  }

  @Override public void hideLoading() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }

  @Override public void switchView(int viewType) {
    List<Ebook> ebooks = ((EbookAdapter) recyclerView.getAdapter()).getItems();
    final EbookAdapter adapter;

    if (viewType == VIEWTYPE_GRID) {
      recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
      recyclerView.setAdapter(adapter = new EbookAdapter(ebooks, R.layout.view_ebook_grid));
    } else if (viewType == VIEWTYPE_LIST) {
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(adapter = new EbookAdapter(ebooks, R.layout.view_ebook_list));
    } else {
      return;
    }

    adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<Ebook>() {
      @Override public void onItemClick(View view, Ebook ebook) {
        presenter.onEbookClick(ebook);
      }
    });
  }

  @Override public void showMessage(String message) {
    Snackbar.make(this.getWindow().getDecorView(), message, Snackbar.LENGTH_LONG).show();
  }
}
