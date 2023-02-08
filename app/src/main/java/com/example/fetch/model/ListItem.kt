package com.example.fetch.model

abstract class ListItem(
) {
    val TYPE_HEADER : Int = 0
    val TYPE_CONTENT : Int = 1
    abstract fun getType() : Int
}

class HeaderItem(val listId: Int) : ListItem() {
    override fun getType(): Int {
        return TYPE_HEADER
    }
}

class ContentItem(val id: String,val name: String?) : ListItem() {
    override fun getType(): Int {
        return TYPE_CONTENT
    }

}