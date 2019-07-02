package com.example.kpukotlin10

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Todo(
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var date: Long = 0,
    var quantity: Long = 0,
    var destination: String = ""
) : RealmObject() {

}