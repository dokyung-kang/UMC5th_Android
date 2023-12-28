package com.example.flo.ui.main.album

import com.example.flo.data.remote.AlbumChartResult

interface AlbumView {
    fun onGetAlbumSuccess(code: Int, result: AlbumChartResult)
    fun onGetAlbumFailure(code: Int, message: String)
}