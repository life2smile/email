package com.example.email.view.home.viewpageritem;

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
import com.example.email.view.detail.viewpageritem.IItemViewProvider;
import com.example.email.viewmodel.OverScrollModel;
import com.example.email.bean.HomeData;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class ItemMoreItemViewProvider implements IItemViewProvider {
    private OverScrollModel mOverScrollModel;

    private boolean needRoteArrowToRight = true;
    private boolean needRoteArrowToLeft = true;

    private boolean needOpenDetailPage = true;

    private boolean releaseWithGoNextPage = false;

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
            mOverScrollModel.getLifeCycleEvent().observeForever(new Observer<String>() {
                @Override
                public void onChanged(String event) {
                    if (event.equals("onResume")) {
                        needOpenDetailPage = true;
                    }
                }
            });
        }
        return view;
    }

    //
    private void updateOverscrollView(ImageView ivArrow, TextView tvMoreNote, int dx) {
        int goNextPageTargetPx = Util.dp2Px(ivArrow.getContext(), 140);
        int roteArrowTargetPx = Util.dp2Px(ivArrow.getContext(), 80);
        tvMoreNote.setText(dx > roteArrowTargetPx ? "释放查看更多" : "滑动查看更多");
        if (dx > roteArrowTargetPx) {
            releaseWithGoNextPage = true;
        }
        boolean goNextPage = dx > goNextPageTargetPx;
        boolean roteArrowToRight = dx > roteArrowTargetPx;
        boolean roteArrowToLeft = dx < roteArrowTargetPx;

        if (roteArrowToRight && needRoteArrowToRight) {
            needRoteArrowToRight = false;
            needRoteArrowToLeft = true;
            rotateImageView(ivArrow, 0, 180);
        }

        if (roteArrowToLeft && needRoteArrowToLeft) {
            needRoteArrowToRight = true;
            needRoteArrowToLeft = false;
            rotateImageView(ivArrow, 180, 0);

            if (releaseWithGoNextPage && needOpenDetailPage) {
                releaseWithGoNextPage = false;
                needOpenDetailPage = false;
                Context context = ivArrow.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
                return;
            }
        }

        if (goNextPage && needOpenDetailPage) {
            needOpenDetailPage = false;
            Context context = ivArrow.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        }
    }

    private void rotateImageView(ImageView imageView, int fromDegrees, int toDegrees) {
        Animation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(200);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDetachWallpaper(true);
        imageView.startAnimation(rotateAnimation);
    }
}