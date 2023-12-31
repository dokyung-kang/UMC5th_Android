package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemSavedsongBinding
import java.util.*

class SaveSongRVAdapter(private val albumList: ArrayList<SaveSong>) : RecyclerView.Adapter<SaveSongRVAdapter.ViewHolder>(){

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        //fun onItemClick(album: SaveSong)
        fun onRemoveSaveSong(position: Int)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SaveSongRVAdapter.ViewHolder {
        val binding: ItemSavedsongBinding = ItemSavedsongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    fun addItem(album: SaveSong){
        albumList.add(album)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        albumList.removeAt(position)
        notifyDataSetChanged()
    }

    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: SaveSongRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])
//        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(albumList[position]) }
        holder.binding.saveSongMore.setOnClickListener { mItemClickListener.onRemoveSaveSong(position) } //삭제됐을 때
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = albumList.size

    // 뷰홀더
    inner class ViewHolder(val binding: ItemSavedsongBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(album: SaveSong){
            binding.saveSongTitle.text = album.title
            binding.saveSongSinger.text = album.singer
            binding.saveSongImg.setImageResource(album.coverImg!!)
        }
    }
}