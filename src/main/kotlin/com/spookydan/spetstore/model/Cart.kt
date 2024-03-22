package com.spookydan.spetstore.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

class Cart : Serializable {
    private val itemMap: MutableMap<String, CartItem> = Collections.synchronizedMap(HashMap())
    private var itemList: MutableList<CartItem> = ArrayList()

    fun getCartItems(): Iterator<CartItem> {
        return itemList.iterator()
    }

    fun getCartItemList(): List<CartItem> {
        return itemList
    }

    fun getNumberOfItems(): Int {
        return itemList.size
    }

    fun getAllCartItems(): Iterator<CartItem> {
        return itemList.iterator()
    }

    fun containsItemId(itemId: String): Boolean {
        return itemMap.containsKey(itemId)
    }

    fun addItem(item: Item, isInStock: Boolean) {
        var cartItem = itemMap[item.itemId]
        if (cartItem == null) {
            cartItem = CartItem(item, 0)
            cartItem.inStock = isInStock
            itemMap[item.itemId] = cartItem
            itemList.add(cartItem)
        }
        cartItem.incrementQuantity()
    }

    fun removeItemById(itemId: String): Item? {
        val cartItem = itemMap.remove(itemId)
        if (cartItem == null) {
            return null
        } else {
            itemList.remove(cartItem)
            return cartItem.item
        }
    }

    fun incrementQuantityByItemId(itemId: String) {
        val cartItem = itemMap[itemId]
        cartItem?.incrementQuantity()
    }

    fun setQuantityByItemId(itemId: String, quantity: Int) {
        val cartItem = itemMap[itemId]
        cartItem?.quantity = quantity
    }

    fun getSubTotal(): BigDecimal {
        var subTotal = BigDecimal("0")
        val items = getAllCartItems()
        while (items.hasNext()) {
            val cartItem = items.next()
            val item = cartItem.item
            val listPrice = item.listPrice
            val quantity = BigDecimal.valueOf(cartItem.quantity.toLong())
            subTotal = subTotal.add(listPrice.multiply(quantity))
        }
        return subTotal
    }

    fun setItemList(cartItemList: List<CartItem>) {
        itemList = cartItemList.toMutableList()
    }
}