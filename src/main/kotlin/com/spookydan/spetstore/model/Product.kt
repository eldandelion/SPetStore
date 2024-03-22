package com.spookydan.spetstore.model

import java.io.Serializable

data class Product(
    var productId: String,
    var categoryId: String,
    var name: String,
    var description: String
) : Serializable {

    constructor() : this("", "", "", "")

    init {
        productId = productId.trim()
    }

    override fun toString(): String {
        return name
    }
}