package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.LineItem

interface LineItemDao {
    fun getLineItemsByOrderId(orderId: Int): List<LineItem>

    fun insertLineItem(lineItem: LineItem)
}