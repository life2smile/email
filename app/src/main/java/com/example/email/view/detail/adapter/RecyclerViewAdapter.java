package com.example.email.view.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.email.R;
import com.example.email.bean.ContentData;
import com.example.email.util.Util;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DetailViewHolder> {
    private List<ContentData> mContentList;

    public void setDataList(List<ContentData> list) {
        mContentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_recycler_view_item, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ContentData contentData = mContentList.get(position);
        holder.mTitleTv.setText(contentData.getTitle());
        GlideUrl glideUrl = new GlideUrl(contentData.getImgUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
                .build());
        int overrideSize = Util.dp2Px(holder.mImageIv.getContext(), 64);
        Glide.with(holder.mImageIv)
                .load(glideUrl).override(overrideSize, overrideSize).into(holder.mImageIv);
    }

    @Override
    public int getItemCount() {
        return mContentList == null ? 0 : mContentList.size();
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        ImageView mImageIv;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mImageIv = itemView.findViewById(R.id.iv_image);
        }
    }
}
