package com.example.kpukotlin3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private fun saveData(a: Int, b: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_A", a)
            .putInt("KEY_B", b)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val a = pref.getInt("KEY_A", 0)
        val b = pref.getInt("KEY_B", 0)
        aInput.setText(a.toString())
        bInput.setText(a.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        plus.setOnClickListener {
            saveData(
                aInput.text.toString().toInt(),
                bInput.text.toString().toInt()
            )
            startActivity<ResultActivity>(
                "result" to (aInput.text.toString().toDouble() + bInput.text.toString().toDouble()).toString()
            )
        }
        minus.setOnClickListener {
            saveData(aInput.text.toString().toInt(),
                bInput.text.toString().toInt())
            startActivity<ResultActivity>(
                "result" to (aInput.text.toString().toDouble() - bInput.text.toString().toDouble()).toString())
        }
        mul.setOnClickListener {
            saveData(aInput.text.toString().toInt(),
                bInput.text.toString().toInt())
            startActivity<ResultActivity>(
                "result" to (aInput.text.toString().toDouble() * bInput.text.toString().toDouble()).toString())
        }
        div.setOnClickListener {
            saveData(aInput.text.toString().toInt(),
                bInput.text.toString().toInt())
            if(bInput.text.toString().toInt() == 0) {
                toast("0으로 나누지 마세요.")
            }
            else {
                startActivity<ResultActivity>(
                    "result" to (aInput.text.toString().toDouble() / bInput.text.toString().toDouble()).toString())
            }
        }
    }
}
