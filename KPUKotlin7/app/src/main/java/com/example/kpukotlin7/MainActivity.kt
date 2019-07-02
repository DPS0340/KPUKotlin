package com.example.kpukotlin7

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var selected: EditText

    fun saveData(res: Double) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        val Anum = A.text.toString().toInt()
        val Bnum = B.text.toString().toInt()

        editor.putInt("KEY_A", Anum).putInt("KEY_B", Bnum).putFloat("KEY_RES", res.toFloat()).apply()
    }

    fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val Anum = pref.getInt("KEY_A", 0)
        val Bnum = pref.getInt("KEY_B", 0)
        val Resnum = pref.getFloat("KEY_RES", 0.0f)

        if(Anum != 0 && Bnum != 0 && Resnum != 0.0f) {
            A.setText(Anum.toString())
            B.setText(Bnum.toString())
            Res.setText("계산 결과: " + Resnum.toString())
        }
    }

    fun result(Num: Double?) {
        Res.setText("계산 결과: " + Num.toString())
    }

    fun inputNumber(pressingKey: Button) {
        selected.setText(selected.text.toString() + pressingKey.text.toString())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selected = A

        loadData()

        A.setOnClickListener {
            selected = A
        }
        B.setOnClickListener {
            selected = B
        }
        button0.setOnClickListener {
            inputNumber(button0)
        }
        button1.setOnClickListener {
            inputNumber(button1)
        }
        button2.setOnClickListener {
            inputNumber(button2)
        }
        button3.setOnClickListener {
            inputNumber(button3)
        }
        button4.setOnClickListener {
            inputNumber(button4)
        }
        button5.setOnClickListener {
            inputNumber(button5)
        }
        button6.setOnClickListener {
            inputNumber(button6)
        }
        button7.setOnClickListener {
            inputNumber(button7)
        }
        button8.setOnClickListener {
            inputNumber(button8)
        }
        button9.setOnClickListener {
            inputNumber(button9)
        }
        plus.setOnClickListener {
            result((A.text.toString().toInt() + B.text.toString().toInt()).toDouble())
            saveData((A.text.toString().toInt() + B.text.toString().toInt()).toDouble())
        }
        minus.setOnClickListener {
            result((A.text.toString().toInt() - B.text.toString().toInt()).toDouble())
            saveData((A.text.toString().toInt() - B.text.toString().toInt()).toDouble())
        }
        mul.setOnClickListener {
            result((A.text.toString().toInt() * B.text.toString().toInt()).toDouble())
            saveData((A.text.toString().toInt() * B.text.toString().toInt()).toDouble())
        }
        div.setOnClickListener {
            result(A.text.toString().toDouble() / B.text.toString().toDouble())
            saveData(A.text.toString().toDouble() / B.text.toString().toDouble())
        }
        remove_a.setOnClickListener {
            A.setText("")
        }
        remove_b.setOnClickListener {
            B.setText("")
        }
        remove_all.setOnClickListener {
            A.setText("")
            B.setText("")
            Res.setText("계산 결과: ")
        }
    }
}
