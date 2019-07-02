package com.example.kpukotlin9

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

class TodoListAdapter(realmResult: OrderedRealmCollection<Todo>) : RealmBaseAdapter<Todo>(realmResult) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_todo, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        if(adapterData != null) {
            val item = adapterData!![position]
            vh.textTextView.text = item.title
            vh.dateTextView.text = DateFormat.format("MM/dd", item.date)
        }
        return view
    }
}

class ViewHolder(view: View) {
    val dateTextView: TextView = view.findViewById(R.id.text1)
    val textTextView: TextView = view.findViewById(R.id.text2)
}