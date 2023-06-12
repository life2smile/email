package com.example.email.view.detail.viewpageritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.email.R;
import com.example.email.bean.ContentData;
import com.example.email.bean.DetailData;
import com.example.email.view.detail.adapter.RecyclerViewAdapter;
import com.example.email.viewmodel.DetailViewModel;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PagerViewProvider implements IItemViewProvider {
    private DetailViewModel mDetailViewModel;

    @Override
    public View getView(Context context, DetailData detailData) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pager_recycler_view, null);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        if (mDetailViewModel == null) {
            mDetailViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(DetailViewModel.class);
            mDetailViewModel.getContentDataList().observeForever(new Observer<List<ContentData>>() {
                @Override
                public void onChanged(List<ContentData> contentData) {
                    if (detailData.getType().equals(contentData.get(0).getType())) {
                        updateView(adapter, contentData);
                    }
                }
            });
            mDetailViewModel.requestContentData(detailData.getType());
        }
        return view;
    }

    private void updateView(RecyclerViewAdapter adapter, List<ContentData> contentData) {
        adapter.setDataList(contentData);
    }
}
