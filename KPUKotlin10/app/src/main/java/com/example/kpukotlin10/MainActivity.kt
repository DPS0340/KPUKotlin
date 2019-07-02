package com.example.kpukotlin10

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()

    var currentid: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val realmResult = realm.where<Todo>().findAll().sort("date", Sort.DESCENDING)

        val adapter = TodoListAdapter(realmResult)
        listView.adapter = adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            startActivity<EditActivity>("id" to id)
            true
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            currentid = id
        }

        delete.setOnClickListener {
            startActivity<EditActivity>("id" to currentid)
        }

        append.setOnClickListener {
            startActivity<EditActivity>()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}
