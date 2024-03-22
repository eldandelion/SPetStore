package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.Product

interface ProductDao {
    fun getProductListByCategory(categoryId: String): List<Product>

    fun getProduct(productId: String): Product?

    fun searchProductList(keywords: String): List<Product>
}