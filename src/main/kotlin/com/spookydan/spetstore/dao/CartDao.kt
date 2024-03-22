package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.CartItem
import com.spookydan.spetstore.model.Item

interface CartDao {
    fun insertCartItem(cartItem: CartItem, userid: String)

    fun getCartItemListBy(userId: String): List<CartItem>

    fun incrementQuantity(userId: String, itemId: String)

    fun decrementQuantity(userId: String, itemId: String)

    fun removeItemById(userId: String, cartItem: CartItem)

    fun removeItemById(userid: String, itemId: String)

    fun updateQuantity(userId: String, cartItem: CartItem, quantity: Int)

    fun removeAllCartItemsBy(userId: String)

    fun getItem(itemId: String): Item?
}