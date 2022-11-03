package com.example.hw5.model


sealed class DataItem {
    data class Singer (
        val title: String,
        val desc: String,
        val url: String
    ): DataItem()

    data class Song (
        val id: Int,
        val title: String,
        val desc: String,
        val images: List<String>,
    ): DataItem()
}