package com.example.flo.ui.main.locker.musicfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.R
import com.example.flo.data.entities.MusicFile
import com.example.flo.databinding.FragmentMusicfileBinding

class MusicFileFragment : Fragment() {

    lateinit var binding : FragmentMusicfileBinding
    private var musicFileDatas = ArrayList<MusicFile>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicfileBinding.inflate(inflater, container, false)

        musicFileDatas.apply {
            add(MusicFile("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(MusicFile("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(MusicFile("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(MusicFile("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(MusicFile("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(MusicFile("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
            add(MusicFile("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(MusicFile("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(MusicFile("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(MusicFile("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(MusicFile("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(MusicFile("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
        }

        // 더미데이터랑 Adapter 연결
        val musicFileRVAdapter = MusicFileRVAdapter(musicFileDatas)
        // 리사이클러뷰에 어댑터를 연결
        binding.musicfileRv.adapter = musicFileRVAdapter

        musicFileRVAdapter.setMyItemClickListener(object : MusicFileRVAdapter.MyItemClickListener {
            override fun onRemoveMusicFile(position: Int) {
                musicFileRVAdapter.removeItem(position)
            }
        })

        return binding.root
    }
}