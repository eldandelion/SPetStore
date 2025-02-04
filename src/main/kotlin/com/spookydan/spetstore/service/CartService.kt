package com.spookydan.spetstore.service

import com.spookydan.spetstore.mappers.CartMapper
import com.spookydan.spetstore.model.CartItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.logging.Logger


@Service
class CartService {


    @Autowired
    private lateinit var cartMapper: CartMapper

    val itemList: List<CartItem>
        get() = _itemList
    private val _itemList: MutableList<CartItem> = ArrayList<CartItem>()

    private var userId: String? = null

    fun bind(userId: String): List<CartItem> {
        //to figure if we need to perform operations on the database
        this.userId = userId
        //getting list from the database
        val accountList = mutableListOf<CartItem>()
            .apply {
                this.addAll(cartMapper.getCartItemListBy(userId))
            }

        //if there are items with the same id, the quantity will be incremented
        //and the item will be removed from the first list

        //TODO fix the bug that causes duplicates
        if (_itemList.size > 0) {
            val iterator = _itemList.iterator()
            while (iterator.hasNext()) {
                val i = iterator.next()
                accountList.forEach { j ->
                    if (i.item.itemId == j.item.itemId) {
                        incrementQuantity(userId, i.item.itemId)
                        iterator.remove()
                    }
                }
            }
            insert(_itemList, userId)
        }
        //if after that there are still items present in the first list,
        //they will be added to the second and put into _itemList
        val newCart = cartMapper.getCartItemListBy(userId)
        _itemList.clear()
        _itemList.addAll(newCart)
        return itemList
    }

    fun insert(item: CartItem, userId: String? = this.userId) {
        _itemList.add(item)
        userId?.let { userId ->
            cartMapper.insertCartItem(item, userId)
        }
    }

    private fun insert(list: List<CartItem>, userId: String? = this.userId) {
        userId?.let { userId ->
            list.forEach {
                cartMapper.insertCartItem(it, userId)
            }
        }
    }

    fun incrementQuantity(itemId: String, userId: String? = this.userId) {
        userId?.let {
            cartMapper.incrementQuantity(it, itemId)
        }
        _itemList.forEach {
            if (it.item.itemId == itemId) {
                it.incrementQuantity()
            }
        }

    }

    fun decrementQuantity(itemId: String, userId: String? = this.userId) {

        _itemList.forEach {
            if (it.item.itemId == itemId && it.quantity > 0) {
                it.decrementQuantity()
                userId?.let { userId ->
                    cartMapper.decrementQuantity(userId, itemId)
                }
            }
        }
    }

    fun calculateSubTotal(): BigDecimal {
        var subTotal = BigDecimal.ZERO
        _itemList.forEach {
            it.total?.let {
                subTotal = subTotal.plus(it)
            }
        }
        return subTotal
    }

    private val logger = Logger.getLogger("CartService")
    fun clear(userId: String? = this.userId) {
        logger.info("User id is $userId")
        userId?.let {
            _itemList.forEach {
                logger.info(it.item.itemId)
                cartMapper.removeItemById(userId, it.item.itemId)
            }
            _itemList.clear()
        }
    }


    fun removeBy(itemId: String, userId: String? = this.userId) {
        val iterator = _itemList.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.item.itemId == itemId) {
                iterator.remove()
            }
        }
        userId?.let {
            cartMapper.removeItemById(userId, itemId)

        }
    }
}