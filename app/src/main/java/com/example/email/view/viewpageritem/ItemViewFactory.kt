package com.example.email.view.viewpageritem

import android.content.Context
import android.view.View
import com.example.email.viewmodel.bean.HomeData
import com.example.email.viewmodel.bean.ViewType

class ViewProvider {
    companion object ViewFactory {
        fun createView(context: Context, homeData: HomeData): View {
            return when (homeData.viewType) {
                ViewType.BANNER_IMAGE -> ItemImageItemViewProvider()
                    .getView(context, homeData)

                ViewType.BANNER_MORE -> ItemMoreItemViewProvider()
                    .getView(
                        context,
                        homeData
                    );
            }
        }
    }
}