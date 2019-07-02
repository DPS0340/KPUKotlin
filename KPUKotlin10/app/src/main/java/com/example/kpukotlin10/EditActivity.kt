//noinspection RestrictedApi
package com.example.kpukotlin10

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.util.*




class EditActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()
    val calendar: Calendar = Calendar.getInstance()

    private fun insertTodo() {
        realm.beginTransaction()
        val todo = realm.createObject<Todo>(nextId())
        todo.title = name.text.toString()
        todo.quantity = quantity.text.toString().toLong()
        todo.destination = dest.text.toString()
        todo.date = calendar.timeInMillis
        realm.commitTransaction()

        alert("내용이 추가되었습니다.") { yesButton { finish() }}.show()
    }

    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if(maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }

    private fun updateTodo(id: Long) {
        realm.beginTransaction()
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todo.title = name.text.toString()
        todo.quantity = quantity.text.toString().toLong()
        todo.destination = dest.text.toString()
        todo.date = calendar.timeInMillis
        realm.commitTransaction()

        alert("내용이 변경되었습니다.") { yesButton { finish() }}.show()
    }

    private fun deleteTodo(id: Long) {
        realm.beginTransaction()
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todo.deleteFromRealm()
        realm.commitTransaction()

        alert("내용이 삭제되었습니다.") { yesButton { finish() }}.show()
    }

    private fun insertMode() {
        deleteFab.hide() // 미지원
        done.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id: Long) {
        val todo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        dest.setText(todo.title)
        calendarView.date = todo.date

        done.setOnClickListener {
            updateTodo(id)
        }

        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra("id", -1L)
        if (id == -1L) insertMode() else updateMode(id)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }
}
