package com.spookydan.spetstore.service

import com.spookydan.spetstore.mappers.CategoryMapper
import com.spookydan.spetstore.mappers.ItemMapper
import com.spookydan.spetstore.mappers.ProductMapper
import com.spookydan.spetstore.model.Category
import com.spookydan.spetstore.model.Item
import com.spookydan.spetstore.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CatalogService(
    @Autowired
    private val productMapper: ProductMapper,
    @Autowired
    private val categoryMapper: CategoryMapper,
    @Autowired
    private val itemMapper: ItemMapper
    ) {



    val categoryList: List<Category>
        get() = categoryMapper.getCategoryList()

    fun getCategory(categoryId: String): Category? {
        return categoryMapper.getCategory(categoryId)
    }

    fun getProduct(productId: String): Product? {
        return productMapper.getProduct(productId)
    }

    fun getProductListByCategory(categoryId: String): List<Product> {
        return productMapper.getProductListByCategory(categoryId)
    }

    // TODO enable using more than one keyword
    fun searchProductList(keyword: String): List<Product> {
        return productMapper.searchProductList("%" + keyword.lowercase(Locale.getDefault()) + "%")
    }

    fun getItemListByProduct(productId: String): List<Item> {
        return itemMapper.getItemListByProduct(productId)
    }

    fun getItem(itemId: String): Item? {
        return itemMapper.getItem(itemId)
    }

    fun isItemInStock(itemId: String): Boolean {
        return itemMapper.getInventoryQuantity(itemId) > 0
    }
}