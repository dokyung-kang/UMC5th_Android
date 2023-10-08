package com.example.timerapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.timerapp2.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var total = 0
    var started = false

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            var min = 0
            var sec = 0
            if(binding.minuteEv.text.toString() != "" || binding.secondEv.text.toString() != "" ) {
                if (binding.minuteEv.text.toString() == "") {
                    min = 0
                    sec = Integer.parseInt(binding.secondEv.text.toString())
                } else if (binding.secondEv.text.toString() == "") {
                    sec = 0
                    min = Integer.parseInt(binding.minuteEv.text.toString())
                } else {
                    sec = Integer.parseInt(binding.secondEv.text.toString())
                    min = Integer.parseInt(binding.minuteEv.text.toString())
                }
                total = min * 60 + sec
                start()
            }
        }
        binding.buttonPause.setOnClickListener {
            pause()
        }
        binding.buttonStop.setOnClickListener {
            stop()
        }
    }

    fun start(){
        started = true
        binding.textTimer.text = formatTime(total)
        // sub thread
        thread(start=true){
            while(true){
                Thread.sleep(1000)
                if(!started)
                    break
                if(total == 0)
                    break
                total = total - 1
                runOnUiThread{
                    binding.textTimer.text = formatTime(total)
                }
            }
        }

    }

    fun pause(){
        if(started) {
            binding.buttonPause.text = "계속"
            started = false
        }
        else {
            binding.buttonPause.text = "정지"
            started = true
            start()
        }
    }

    fun stop(){
        started = false
        total = 0
        binding.textTimer.text = "00 : 00"
    }

    fun formatTime(time:Int): String{
        val minute = String.format("%02d", time/60)
        val second = String.format("%02d", time%60)
        return "$minute : $second"
    }
}