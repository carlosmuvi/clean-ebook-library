package carlosmuvi.bqsample.ui.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by carlos.
 */
public class EbookRecyclerView extends RecyclerView {

  private GridLayoutManager manager;
  private int columnWidth = -1;

  private View emptyView;

  private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

    @Override public void onChanged() {
      Adapter<?> adapter = getAdapter();
      if (adapter != null && emptyView != null) {
        if (adapter.getItemCount() == 0) {
          emptyView.setVisibility(View.VISIBLE);
          EbookRecyclerView.this.setVisibility(View.GONE);
        } else {
          emptyView.setVisibility(View.GONE);
          EbookRecyclerView.this.setVisibility(View.VISIBLE);
        }
      }
    }
  };

  public EbookRecyclerView(Context context) {
    super(context);
    init(context, null);
  }

  public EbookRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public EbookRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {

    if (attrs != null) {
      int[] attrsArray = {
          android.R.attr.columnWidth
      };
      TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
      columnWidth = array.getDimensionPixelSize(0, -1);
      array.recycle();
    }

    manager = new GridLayoutManager(getContext(), 1);
    setLayoutManager(manager);
  }

  @Override public void setAdapter(Adapter adapter) {
    super.setAdapter(adapter);

    if (adapter != null) {
      adapter.registerAdapterDataObserver(emptyObserver);
    }

    emptyObserver.onChanged();
  }

  public void setEmptyView(View emptyView) {
    this.emptyView = emptyView;
  }

  @Override protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, heightSpec);
    if (columnWidth > 0) {
      int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
      manager.setSpanCount(spanCount);
    }
  }
}

