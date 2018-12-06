package com.example.v.vikaApp.catslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.v.vikaApp.R;
import com.example.v.vikaApp.entity.CatEntity;
import com.example.v.vikaApp.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<CatEntity> mCats;
    private final OnItemClickListener mListener;

    RecyclerViewAdapter(List<CatEntity> cats, OnItemClickListener listener) {
        mCats = cats;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
            viewHolder.bind(mListener, viewHolder.getAdapterPosition());
        }


    @Override
    public int getItemCount() {
        return mCats.size();
    }

    void clear() {
        mCats.clear();
        notifyDataSetChanged();
    }

    void addAll(List<CatEntity> cats) {
        mCats.addAll(cats);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.imageHeader)
        TextView mTextView;

        ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(final OnItemClickListener listener, final int position) {
            RequestOptions glideOptions = new RequestOptions();
            final int[] codes = {100, 101, 200, 201, 202, 204, 500, 404, 300, 425, 426,
                    207, 307, 410, 411, 420, 421, 422};
            for (final int code : codes) {
                Glide.with(mImageView.getContext()).asBitmap()
                        .load(mCats.get(position).getUrl(code))
                        .apply(glideOptions.centerCrop())
                        .into(mImageView);
                mTextView.setText(mCats.get(position).getId());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(view, mCats.get(position));
                    }
                });
            }
        }
    }
}
