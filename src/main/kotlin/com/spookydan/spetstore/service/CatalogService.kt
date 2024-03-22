package com.spookydan.spetstore.service

import com.spookydan.spetstore.dao.CategoryDao
import com.spookydan.spetstore.dao.ItemDao
import com.spookydan.spetstore.dao.ProductDao
import com.spookydan.spetstore.dao.impl.CategoryDaoImpl
import com.spookydan.spetstore.dao.impl.ItemDaoImpl
import com.spookydan.spetstore.dao.impl.ProductDaoImpl
import com.spookydan.spetstore.model.Category
import com.spookydan.spetstore.model.Item
import com.spookydan.spetstore.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CatalogService(
    @Autowired
    @Qualifier("productDao")
    private val productDao: ProductDao,
    @Autowired
    @Qualifier("categoryDao")
    private val categoryDao: CategoryDao,
    @Autowired
    @Qualifier("itemDao")
    private val itemDao: ItemDao
    ) {



    val categoryList: List<Category>
        get() = categoryDao.getCategoryList()

    fun getCategory(categoryId: String): Category? {
        return categoryDao.getCategory(categoryId)
    }

    fun getProduct(productId: String): Product? {
        return productDao.getProduct(productId)
    }

    fun getProductListByCategory(categoryId: String): List<Product> {
        return productDao.getProductListByCategory(categoryId)
    }

    // TODO enable using more than one keyword
    fun searchProductList(keyword: String): List<Product> {
        return productDao.searchProductList("%" + keyword.lowercase(Locale.getDefault()) + "%")
    }

    fun getItemListByProduct(productId: String): List<Item> {
        return itemDao.getItemListByProduct(productId)
    }

    fun getItem(itemId: String): Item? {
        return itemDao.getItem(itemId)
    }

    fun isItemInStock(itemId: String): Boolean {
        return itemDao.getInventoryQuantity(itemId) > 0
    }
}