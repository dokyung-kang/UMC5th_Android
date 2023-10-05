package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myapplication2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 핸들러 만들어줌
        val handler = Handler(Looper.getMainLooper())
        
        val imageList = arrayListOf<Int>()

        imageList.add(R.drawable.bear)
        imageList.add(R.drawable.duke)
        imageList.add(R.drawable.just)
        imageList.add(R.drawable.bear)
        imageList.add(R.drawable.duke)
        imageList.add(R.drawable.just)
        imageList.add(R.drawable.bear)
        imageList.add(R.drawable.duke)
        imageList.add(R.drawable.just)

        // Main thread에서만 뷰렌더링 가능 but work thread에서 시도하면 에러남
        // 1. 핸들러 만들어야 함
        Thread {
            for(image in imageList){
                runOnUiThread{
                    binding.iv.setImageResource(image)
                }
                Thread.sleep(2000)
            }
        }.start()

    }
}