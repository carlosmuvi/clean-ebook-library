package carlosmuvi.bqsample.ui.adapters;

import android.view.View;

public interface OnRecyclerViewItemClickListener<Ebook> {
  void onItemClick(View view, Ebook ebook);
}