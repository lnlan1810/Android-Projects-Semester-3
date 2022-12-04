package com.example.musicapp

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Music (
    val id: Int,
    val title: String,
    val author:String,
    @DrawableRes val cover:Int,
    @RawRes val song:Int
)