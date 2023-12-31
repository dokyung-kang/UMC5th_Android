package com.example.flo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import me.relex.circleindicator.CircleIndicator3

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeTodayMusicImg1Iv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AlbumFragment())
                .commitAllowingStateLoss()
        }

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


}