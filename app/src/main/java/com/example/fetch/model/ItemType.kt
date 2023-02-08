package com.example.fetch.model

enum class ItemType(val i: Int) {
    TYPE_HEADER(0),
    TYPE_CONTENT(1);

    public fun getValue() : Int {
        return i
    }
}