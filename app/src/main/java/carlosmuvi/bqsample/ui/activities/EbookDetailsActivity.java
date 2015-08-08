package carlosmuvi.bqsample.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import carlosmuvi.bqsample.BqSampleApp;
import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.di.ActivityModule;
import carlosmuvi.bqsample.di.components.DaggerEbookDetailsComponent;
import carlosmuvi.bqsample.di.components.EbookDetailsComponent;
import carlosmuvi.bqsample.model.Ebook;
import carlosmuvi.bqsample.presenters.EbookDetailsPresenter;
import carlosmuvi.bqsample.ui.activities.base.BaseActivity;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;

public class EbookDetailsActivity extends BaseActivity implements EbookDetailsPresenter.View {

  /**
   * *********************
   * Dependency injection
   * *********************
   */

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

  @Inject EbookDetailsPresenter presenter;

  /**
   * *********************
   * Activity lifecycle
   * *********************
   */

  @Bind(R.id.collapsing_toolbar) public CollapsingToolbarLayout collapsingToolbar;
  @Bind(R.id.backdrop) public ImageView backdrop;
  @Bind(R.id.tv_author) public TextView author;
  @Bind(R.id.tv_created) public TextView created;
  @Bind(R.id.tv_path) public TextView path;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    component().inject(this);

    presenter.setView(this);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_ebook_details;
  }

  /**
   * *********************
   * View Inherited
   * *********************
   */

  @Override public void showBook(Ebook ebook) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US);

    collapsingToolbar.setTitle(ebook.getTitle());
    author.setText(ebook.getAuthor());
    created.setText(sdf.format(ebook.getCreated()));
    path.setText(ebook.getPath());

    if (ebook.getCover() != null) {
      Glide.with(this).load(ebook.getCover()).fitCenter().into(backdrop);
    } else {
      Glide.with(this).load(R.drawable.no_cover).fitCenter().into(backdrop);
    }
  }
}
