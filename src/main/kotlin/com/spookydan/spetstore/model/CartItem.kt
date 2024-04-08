package com.spookydan.spetstore.model

import org.springframework.format.annotation.NumberFormat
import java.io.Serializable
import java.math.BigDecimal

data class CartItem(
    var item: Item,
    var quantity: Int,
    var inStock: Boolean = false,
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    var total: BigDecimal? = null
) : Serializable {

    constructor() : this(Item(), 0, false, null)


    fun incrementQuantity() {
        quantity++
        calculateTotal()
    }

    fun decrementQuantity() {
        if (quantity > 0) {
            quantity--
            calculateTotal()
        }
    }

    fun calculateTotal() {
        total = item.listPrice.multiply(quantity.toBigDecimal())
    }
}