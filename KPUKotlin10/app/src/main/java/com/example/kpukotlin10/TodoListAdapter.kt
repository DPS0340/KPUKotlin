package com.example.kpukotlin10

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
            vh.destTextView.text = item.destination
            vh.quantityTextView.text = item.quantity.toString()
        }
        return view
    }
}

class ViewHolder(view: View) {
    val dateTextView: TextView = view.findViewById(R.id.title)
    val textTextView: TextView = view.findViewById(R.id.name)
    val destTextView: TextView = view.findViewById(R.id.destination)
    val quantityTextView: TextView = view.findViewById(R.id.quantity)
}