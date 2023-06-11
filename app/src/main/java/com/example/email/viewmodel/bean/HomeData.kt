package com.example.email.viewmodel.bean


enum class ViewType {
    BANNER_IMAGE,
    BANNER_MORE
}

data class HomeData(
    val viewType: ViewType,
    val imgUrl: String?,
    val title: String?,
    val desc: String?
) {
    constructor(viewType: ViewType) : this(viewType, "", "", "") {

    }
}