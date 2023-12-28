package com.example.flo.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.data.remote.AlbumChartResult
import com.example.flo.data.remote.AlbumChartSongs
import com.example.flo.ui.main.album.AlbumRVAdapter
import com.example.flo.data.remote.AlbumService
import com.example.flo.ui.main.album.AlbumView
import com.example.flo.R
import com.example.flo.data.remote.SongDatabase
import com.example.flo.data.entities.Album
import com.example.flo.databinding.FragmentHomeBinding
import com.example.flo.ui.main.MainActivity
import com.example.flo.ui.main.album.AlbumFragment
import com.google.gson.Gson

class HomeFragment : Fragment(), AlbumView {

    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()
    private lateinit var albumAdapter: AlbumRVAdapter

    private lateinit var songDB: SongDatabase

    var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        songDB = SongDatabase.getInstance(requireContext())!!
        albumDatas.addAll(songDB.albumDao().getAlbums()) // songDB에서 album list를 가져옵니다.
        Log.d("albumlist", albumDatas.toString())

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val pannelAdapter = PannelVPAdapter(this)
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        binding.homePannelBackgroundVp.adapter = pannelAdapter
        binding.homePannelBackgroundVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val pannelIndicator = binding.homePannelIndicator
        pannelIndicator.setViewPager(binding.homePannelBackgroundVp)

        val thread = Thread(PagerRunnable())
        thread.start()

        return binding.root
    }

    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }

    val handler = Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    fun setPage(){
        if(currentPage == 6)
            currentPage = 0
        binding.homePannelBackgroundVp.setCurrentItem(currentPage, true)
        currentPage += 1
    }

    private fun changeAlbumFragment(albumChartSongs: AlbumChartSongs) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(albumChartSongs)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }

    override fun onStart() {
        super.onStart()
        getAlbums()
    }

    private fun initRecyclerView(result: AlbumChartResult) {
        albumAdapter = AlbumRVAdapter(requireContext(), result)

        binding.homeTodayMusicAlbumRv.adapter = albumAdapter

        albumAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(albumChartSongs: AlbumChartSongs) {
                changeAlbumFragment(albumChartSongs)
            }

            override fun onRemoveAlbum(position: Int) {
                albumAdapter.removeItem(position)
            }
        })
    }

    private fun getAlbums() {
        val albumService = AlbumService()
        albumService.setAlbumView(this)

        albumService.getAlbums()

    }

    override fun onGetAlbumSuccess(code: Int, result: AlbumChartResult) {
        initRecyclerView(result)
    }

    override fun onGetAlbumFailure(code: Int, message: String) {
        Log.d("LOOK-FRAG/SONG-RESPONSE", message)
    }


}