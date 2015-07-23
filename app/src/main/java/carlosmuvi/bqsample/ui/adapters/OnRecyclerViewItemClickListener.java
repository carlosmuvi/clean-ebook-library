package carlosmuvi.bqsample.ui.adapters;

import android.view.View;

public interface OnRecyclerViewItemClickListener<Ebook> {
    public void onItemClick(View view, Ebook ebook);
}