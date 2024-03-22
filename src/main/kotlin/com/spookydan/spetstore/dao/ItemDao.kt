package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.Item

interface ItemDao {
    fun updateInventoryQuantity(param: Map<String, Any>)

    fun getInventoryQuantity(itemId: String): Int

    fun getItemListByProduct(productId: String): List<Item>

    fun getItem(itemId: String): Item?
}