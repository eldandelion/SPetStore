package com.spookydan.spetstore.model

import java.io.Serializable
import java.math.BigDecimal

data class LineItem(
    var orderId: Int = 0,
    var lineNumber: Int = 0,
    var quantity: Int = 0,
    var itemId: String = "",
    var unitPrice: BigDecimal? = null,
    var item: Item? = null,
    var total: BigDecimal? = null
) : Serializable {





    constructor(lineNumber: Int, cartItem: CartItem) : this() {
        this.lineNumber = lineNumber
        this.quantity = cartItem.quantity
        this.itemId = cartItem.item.itemId
        this.unitPrice = cartItem.item.listPrice
        this.item = cartItem.item
        this.total = cartItem.total
        calculateTotal()
    }





    fun calculateTotal() {
        total = item?.listPrice?.multiply(quantity.toBigDecimal())
    }
}