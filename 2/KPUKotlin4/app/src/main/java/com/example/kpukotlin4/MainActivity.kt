package com.example.kpukotlin4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var time = 0
        var timerTask: Timer? = null
        var isRunning = false
        var lap = 1

        fun start() {
            startFab.setImageResource(R.drawable.ic_pause_black_24dp)

            timerTask = timer(period=10) {
                time++
                val sec = time / 100
                val milli = time % 100
                runOnUiThread {
                    secTextView.text = "$sec"
                    milliTextView.text = "$milli"
                }
            }
        }

        fun pause() {
            startFab.setImageResource(R.drawable.ic_play_arrow_black_24dp)

            timerTask?.cancel()
        }

        fun recordLapTime() {
            val lapTime = time
            val textView = TextView(this)
            textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

            lapLayout.addView(textView, 0)
            lap++
        }

        fun reset() {
            timerTask?.cancel()

            time = 0
            isRunning = false
            startFab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            secTextView.text = "0"
            milliTextView.text = "00"

            lapLayout.removeAllViews()
            lap = 1
        }

        startFab.setOnClickListener {
            isRunning = !isRunning

            if(isRunning) start() else pause()
        }

        lapButton.setOnClickListener {
            recordLapTime()
        }

        resetFab.setOnClickListener {
            reset()
        }
    }
}
