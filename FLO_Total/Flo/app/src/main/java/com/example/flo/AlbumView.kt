package com.example.flo

interface AlbumView {
    fun onGetAlbumSuccess(code: Int, result: AlbumChartResult)
    fun onGetAlbumFailure(code: Int, message: String)
}