package com.example.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.timerapp.databinding.ActivityMainBinding
import java.util.Timer

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var timer: Timer
    var timedata = TimeData(0, false)
    var minute: Int = 0
    var second: Int = 0
    var totalSecond: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exitBtn.setOnClickListener{
            finish()
        }
        binding.startBtn.setOnClickListener {
            if(binding.minuteEv.text.toString() != "" || binding.secondEv.text.toString() != "" ) {
                if (binding.minuteEv.text.toString() == "") {
                    minute = 0
                    second =  Integer.parseInt(binding.secondEv.text.toString())
                }
                else if (binding.secondEv.text.toString() == "") {
                    second = 0
                    minute = Integer.parseInt(binding.minuteEv.text.toString())
                }
                else{
                    second =  Integer.parseInt(binding.secondEv.text.toString())
                    minute = Integer.parseInt(binding.minuteEv.text.toString())
                }
                totalSecond = minute * 60 + second

                initTime()
                setTimer(timedata)
                setTimeStatus(true)
            }
            else{
                Toast.makeText(this, "시간을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
        binding.resumeBtn.setOnClickListener {
            setTimeStatus(true)
        }
        binding.pauseBtn.setOnClickListener {
            if(binding.minuteEv.text.toString() != "" || binding.secondEv.text.toString() != "" ) {
                setTimeStatus(false)
            }
            else{
                Toast.makeText(this, "시간을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    private fun initTime(){

        timedata = TimeData(
            totalSecond,
            false
        )
        startTimer()
    }

    private fun setTimer(timedata: TimeData){
        binding.timeTv.text = String.format("%02d:%02d", timedata.playTime / 60, timedata.playTime % 60)
    }

    fun setTimeStatus(isPlaying : Boolean){
        timedata.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){
            binding.resumeBtn.visibility = View.GONE
            binding.pauseBtn.visibility = View.VISIBLE
        }
        else {
            binding.resumeBtn.visibility = View.VISIBLE
            binding.pauseBtn.visibility = View.GONE
        }
    }

    private fun startTimer(){
        timer = Timer(timedata.playTime, timedata.isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true): Thread() {
        private var second : Int = playTime
        private var mills: Float = 0f

        override fun run() {
            super.run()
            try {
                while(true){

                    if(second <= 0){
                        timedata.playTime = 0
                        timedata.isPlaying = false
                        break
                    }

                    if(isPlaying){
                        sleep(50)
                        mills += 50
                        Log.d("times", "${mills}")

                        if (mills % 1000 == 0f){
                            runOnUiThread{
                                binding.timeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                            }
                            second--
                        }
                    }
                }
            }catch (e: InterruptedException){
                Log.d("Timedata", "쓰레드가 죽었습니다. ${e.message}")
            }

        }
    }
}