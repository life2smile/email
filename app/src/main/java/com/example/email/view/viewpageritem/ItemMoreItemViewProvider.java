package com.example.email.view.viewpageritem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.email.R;
import com.example.email.activity.DetailActivity;
import com.example.email.util.Util;
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
            TextView tvMoreNote = view.findViewById(R.id.tv_more_text);
            mOverScrollModel = new ViewModelProvider((ViewModelStoreOwner) context).get(OverScrollModel.class);
            mOverScrollModel.getPositionOffsetPixels().observeForever(new Observer<Integer>() {
                @Override
                public void onChanged(Integer offset) {
                    updateOverscrollView(ivArrow, tvMoreNote, offset);
                }
            });
        }
        return view;
    }

    private boolean roteToRight = false;
    private boolean roteToLeft = false;

    private boolean needOpenDetailPage = true;

    //
    private void updateOverscrollView(ImageView ivArrow, TextView tvMoreNote, int dx) {
        int targetPx = Util.dp2Px(ivArrow.getContext(), 120);

        if (dx > targetPx && !roteToRight) {
            roteToRight = true;
            roteToLeft = false;
            Animation rotateAnimation = new RotateAnimation(0, 180,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(10);
            rotateAnimation.setRepeatCount(0);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDetachWallpaper(true);
            ivArrow.startAnimation(rotateAnimation);
        }

        if (dx < targetPx && !roteToLeft) {
            roteToRight = false;
            roteToLeft = true;
            Animation rotateAnimation = new RotateAnimation(180, 0,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(10);
            rotateAnimation.setRepeatCount(0);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDetachWallpaper(true);
            ivArrow.startAnimation(rotateAnimation);
        }

        tvMoreNote.setText(dx > targetPx ? "释放查看更多" : "滑动查看更多");
        if (dx > targetPx && needOpenDetailPage) {
            needOpenDetailPage = false;
            Context context = ivArrow.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        }
    }
}