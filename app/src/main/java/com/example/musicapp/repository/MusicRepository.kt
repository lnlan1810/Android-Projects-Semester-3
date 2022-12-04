package com.example.musicapp.repository

import com.example.musicapp.Music
import com.example.musicapp.R

object MusicRepository {
    var id = 0;

    val musics = arrayListOf(
        Music(id++, "Hay Trao Cho Anh","Son Tung MTP", R.drawable.hay_trao_cho_anh, R.raw.hay_trao),
        Music(id++, "Khi Em Lon", "Orange", R.drawable.khi_em_lon , R.raw.khi_em_lon),
        Music(id++, "Muon Roi Ma Sao Con", "Son Tung MTP", R.drawable.muon_roi_ma_sao_con , R.raw.muon_roi_ma),
        Music(id++, "Noi Nay Co Anh", "Son Tung MTP", R.drawable.noi_nay_co_anh , R.raw.noi_nay),
        Music(id++, "Muon Ruou To Tinh", "Tien Cookie", R.drawable.muon_ruou_to_tinh , R.raw.muon_ruou),
        Music(id++, "Lo Say Bye La Bye", "Changg", R.drawable.lo_say_bye , R.raw.lo_say_bye),
    )
}