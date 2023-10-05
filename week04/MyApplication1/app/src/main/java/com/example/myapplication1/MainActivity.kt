package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 객체 만듦
        val a = A()
        val b = B()

        // thread 시작
        a.start()
        a.join()    // a thread가 끝나면 b thread 시작할 수 있게
        b.start()
    }

    // 클래스 A 만들고 Thread 상속
    class A : Thread() {
        override fun run() {    // run 함수 override
            super.run() // thread가 시작함과 동시에 실행
            for (i in 1..1000){
                Log.d("test", "first : $i")
            }
        }
    }

    class B : Thread() {
        override fun run() {
            super.run()
            for (i in 1000 downTo 1){
                Log.d("test", "second : $i")
            }
        }
    }
}