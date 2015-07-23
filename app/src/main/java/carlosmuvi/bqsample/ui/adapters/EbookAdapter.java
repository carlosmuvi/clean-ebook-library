package carlosmuvi.bqsample.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import carlosmuvi.bqsample.R;
import carlosmuvi.bqsample.model.Ebook;

/**
 * Created by carlos.
 */
public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.ViewHolder> implements View.OnClickListener {

    List<Ebook> ebooks;
    private int itemLayout;
    private OnRecyclerViewItemClickListener<Ebook> itemClickListener;

    public EbookAdapter(List<Ebook> ebooks, int itemLayout) {
        this.ebooks = ebooks;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ebook ebook = ebooks.get(position);
        holder.tv_ebook_title.setText(ebook.getTitle());
        if (ebook.getCover() != null) {
            Glide.with(holder.img_ebook_cover.getContext())
                    .load(ebook.getCover()).centerCrop().into(holder.img_ebook_cover);
        } else {
            Glide.with(holder.img_ebook_cover.getContext())
                    .load(R.drawable.no_cover).centerCrop().into(holder.img_ebook_cover);
        }
        holder.itemView.setTag(ebook);
    }

    @Override
    public int getItemCount() {
        return ebooks.size();
    }

    public List<Ebook> getItems() {
        return ebooks;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Ebook> listener) {
        this.itemClickListener = listener;
    }

    public void setEbooks(List<Ebook> ebooks) {
        this.ebooks = ebooks;
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            Ebook ebook = (Ebook) view.getTag();
            itemClickListener.onItemClick(view, ebook);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_ebook_cover;
        public TextView tv_ebook_title;

        public ViewHolder(View itemView) {
            super(itemView);
            img_ebook_cover = (ImageView) itemView.findViewById(R.id.ebook_cover);
            tv_ebook_title = (TextView) itemView.findViewById(R.id.ebook_title);
        }
    }
}

