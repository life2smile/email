package com.example.email.view.viewpageritem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.email.R;
import com.example.email.viewmodel.OverScrollModel;
import com.example.email.viewmodel.bean.HomeData;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ItemMoreItemViewProvider implements IItemViewProvider {
    private OverScrollModel mOverScrollModel;

    @Override
    public View getView(Context context, HomeData homeData) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.viewpager_item_more_view, null, false);
        if (mOverScrollModel == null) {
            ImageView ivArrow = view.findViewById(R.id.iv_arrow);
            mOverScrollModel = new ViewModelProvider((ViewModelStoreOwner) context).get(OverScrollModel.class);
            mOverScrollModel.getOverscrollDx().observeForever(new Observer<Float>() {
                @Override
                public void onChanged(Float dx) {
                    updateOverscrollView(ivArrow, dx);
                }
            });
        }
        return view;
    }

    //
    private void updateOverscrollView(ImageView ivArrow, Float dx) {
        ivArrow.setImageResource(dx > 100 ? R.mipmap.arrow_right : R.mipmap.arrow_left);
    }
}