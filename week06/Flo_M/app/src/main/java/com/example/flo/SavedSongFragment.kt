package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentDetailBinding
import com.example.flo.databinding.FragmentSavedsongBinding
import com.example.flo.databinding.FragmentVideoBinding
import com.google.gson.Gson

class SavedSongFragment : Fragment() {

    lateinit var binding : FragmentSavedsongBinding
    private var saveSongDatas = ArrayList<SaveSong>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedsongBinding.inflate(inflater, container, false)

        saveSongDatas.apply {
            add(SaveSong("Butter", "방탄소년단 (BTS)", R.drawable.img_album_exp))
            add(SaveSong("Lilac", "아이유 (IU)", R.drawable.img_album_exp2))
            add(SaveSong("Next Level", "에스파 (AESPA)", R.drawable.img_album_exp3))
            add(SaveSong("Boy with Luv", "방탄소년단 (BTS)", R.drawable.img_album_exp4))
            add(SaveSong("BBoom BBoom", "모모랜드 (MOMOLAND)", R.drawable.img_album_exp5))
            add(SaveSong("Weekend", "태연 (Tae Yeon)", R.drawable.img_album_exp6))
        }

        // 더미데이터랑 Adapter 연결
        val saveSongRVAdapter = SaveSongRVAdapter(saveSongDatas)
        // 리사이클러뷰에 어댑터를 연결
        binding.saveSongRv.adapter = saveSongRVAdapter

//        saveSongRVAdapter.setMyItemClickListener(object : SaveSongRVAdapter.MyItemClickListener{
//            override fun onRemoveSaveSong(position: Int) {
//                saveSongRVAdapter.removeItem(position)
//            }
//        })

        return binding.root
    }
    private fun changeSaveSongFragment(saveSong: SaveSong) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(saveSong)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }
}