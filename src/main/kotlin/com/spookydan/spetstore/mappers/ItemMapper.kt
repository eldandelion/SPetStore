package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Item
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ItemMapper {
    fun updateInventoryQuantity(param: Map<String, Any>)

    fun getInventoryQuantity(itemId: String): Int

    fun getItemListByProduct(productId: String): List<Item>

    fun getItem(itemId: String): Item?
}