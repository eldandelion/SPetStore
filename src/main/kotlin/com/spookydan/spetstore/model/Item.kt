package com.spookydan.spetstore.model

import org.springframework.format.annotation.NumberFormat
import java.io.Serializable
import java.math.BigDecimal

data class Item(
    var itemId: String,
    var productId: String,
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    var listPrice: BigDecimal,
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    var unitCost: BigDecimal,
    var supplierId: Int,
    var status: String,
    var attribute1: String,
    var attribute2: String,
    var product: Product?,
    var quantity: Int
) : Serializable {

    constructor() : this("", "", BigDecimal.ZERO, BigDecimal.ZERO, 0, "", "", "",  null, 0)

    init {
        itemId = itemId.trim()
    }

    override fun toString(): String {
        return "($itemId-$productId)"
    }
}