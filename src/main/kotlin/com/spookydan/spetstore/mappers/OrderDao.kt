package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Order

interface OrderDao {
    fun getOrdersByUsername(username: String): List<Order>

    fun getOrder(orderId: Int): Order?

    fun insertOrder(order: Order)

    fun insertOrderStatus(order: Order, quantity: Int)
}