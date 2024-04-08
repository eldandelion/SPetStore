package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Product
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper {
    fun getProductListByCategory(categoryId: String): List<Product>

    fun getProduct(productId: String): Product?

    fun searchProductList(keywords: String): List<Product>
}