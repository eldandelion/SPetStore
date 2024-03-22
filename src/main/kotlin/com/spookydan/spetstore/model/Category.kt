package com.spookydan.spetstore.model

import java.io.Serializable

data class Category(
    var categoryId: String = "",
    var name: String = "",
    var description: String = ""
) : Serializable {
    init {
        categoryId = categoryId.trim()
    }

    override fun toString(): String {
        return categoryId
    }
}