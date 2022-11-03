package com.example.hw5.model

object Repository {
    const val  TYPE_SONG = 0
    const val TYPE_SINGER =1

    val songs = arrayListOf(
        DataItem.Song(1,"Starving", "peaked #12 in US Billboard Hot 100", listOf("https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4MDAzMTQw/top-28-hottest-and-most-popular-solo-singers-under-28.webp")),
        DataItem.Song(2,"Starving", "peaked #12 in US Billboard Hot 100", listOf("https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4MDAzMTQw/top-28-hottest-and-most-popular-solo-singers-under-28.webp")),
        DataItem.Song(3,"Royals", "peaked #1 in the US", listOf("https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Nzg5NTcy/top-28-hottest-and-most-popular-solo-singers-under-28.webp", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_960/MTc0OTkyNTcyNTk3OTM3NjA0/top-28-hottest-and-most-popular-solo-singers-under-28.webp")),
    )


    val singers = arrayListOf(
        DataItem.Singer("Mary J. Blige", "May 26th, 1948", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Nzg5NTcy/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Grace VanderWaal ", "January 15, 2004 ", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_960/MTc0OTkyNTcyNTk3OTM3NjA0/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Billie Eilish ", "December 18, 2001", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Nzg5NTcy/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Shawn Mendes", "August 8, 1998", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4MDY4Njc2/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Khalid", "February 11, 1998", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_630/MTc0OTkyNTcyNTk3NDc4ODUy/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Zara Larsson", "December 16, 1997", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Mzk2MzU2/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Camila Cabello ", "January 15, 2004 ", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_960/MTc0OTkyNTcyNTk3OTM3NjA0/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Hailee Steinfeld  ", "December 18, 2001", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Nzg5NTcy/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Shawn Mendes", "August 8, 1998", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4MDY4Njc2/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Khalid", "February 11, 1998", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_630/MTc0OTkyNTcyNTk3NDc4ODUy/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),
        DataItem.Singer("Zara Larsson", "December 16, 1997", "https://images.saymedia-content.com/.image/c_limit%2Ccs_srgb%2Cq_auto:eco%2Cw_700/MTc0OTkyNTcyNTk4Mzk2MzU2/top-28-hottest-and-most-popular-solo-singers-under-28.webp"),

        )

    var dataList: List<DataItem> = listOf()

    fun createList() {
        val insertEvery = 5;
        dataList = singers
            .chunked(insertEvery)
            .flatMap { listOf(songs[0]) + it };
    }

    fun addItem(position: Int, car: DataItem.Singer) {
        dataList = dataList.take(position) + car + dataList.drop(position)
    }

    fun deleteItem(item: DataItem) {
        dataList = dataList.filter {
            it !== item
        }
    }

}