package com.example.email.view.home.viewpageritem

import android.content.Context
import android.view.View
import com.example.email.bean.HomeData
import com.example.email.bean.ViewType
import com.example.email.view.detail.viewpageritem.ItemMoreItemViewProvider
import com.example.email.view.home.ItemImageItemViewProvider

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