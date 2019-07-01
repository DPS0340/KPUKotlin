package com.example.kpukotlin6

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(A)
        registerForContextMenu(B)
        registerForContextMenu(Res)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.plus -> {
                val result = A.text.toString().toInt() + B.text.toString().toInt()
                Res.setText("계산 결과: " + result.toString())
            }
            R.id.minus -> {
                val result = A.text.toString().toInt() - B.text.toString().toInt()
                Res.setText("계산 결과: " + result.toString())
            }
            R.id.mul -> {
                val result = A.text.toString().toInt() * B.text.toString().toInt()
                Res.setText("계산 결과: " + result.toString())
            }
            R.id.div -> {
                val result = A.text.toString().toDouble() / B.text.toString().toDouble()
                Res.setText("계산 결과: " + result.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.remove_a -> {
                A.setText("")
            }
            R.id.remove_b -> {
                B.setText("")
            }
            R.id.remove_all -> {
                A.setText("")
                B.setText("")
                Res.setText("계산 결과: ")
            }
        }
        return super.onContextItemSelected(item)
    }
}
