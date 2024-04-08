package com.spookydan.spetstore.model

import org.apache.ibatis.annotations.Param
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class Order(
    var orderId: Int = 0,
    var username: String = "",
    var orderDate: Date = Date(),
    var shipAddress1: String = "",
    var shipAddress2: String = "",
    var shipCity: String = "",
    var shipState: String = "",
    var shipZip: String = "",
    var shipCountry: String = "",
    var billAddress1: String = "",
    var billAddress2: String = "",
    var billCity: String = "",
    var billState: String = "",
    var billZip: String = "",
    var billCountry: String = "",
    var courier: String = "",
    var totalPrice: BigDecimal? = null,
    var billToFirstName: String = "",
    var billToLastName: String = "",
    var shipToFirstName: String = "",
    var shipToLastName: String = "",
    var creditCard: String = "",
    var expiryDate: String = "",
    var cardType: String = "",
    var locale: String = "",
    var status: String = "",
    var lineItems: List<LineItem> = ArrayList(),
    var subTotal: BigDecimal = BigDecimal.ZERO
) : Serializable {


    fun addLineItem(cartItem: CartItem) {
        val lineItem = LineItem(lineItems.size + 1, cartItem)
        addLineItem(lineItem)
    }

    fun addLineItem(lineItem: LineItem) {
        lineItems.addLast(lineItem)
    }
}