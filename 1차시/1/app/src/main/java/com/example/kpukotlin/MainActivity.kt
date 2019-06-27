package com.example.kpukotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        clickButton.setOnClickListener {
            if(count % 2 == 0) textView.text = "버튼이 눌렸습니다."
            else textView.text = "헬로 월드"
            count += 1
        }
    }
}
