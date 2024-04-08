package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.LineItem

interface LineItemMapper {
    fun getLineItemsByOrderId(orderId: Int): List<LineItem>

    fun insertLineItems(lineItems: List<LineItem>)
}