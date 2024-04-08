package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.ProductMapper
import com.spookydan.spetstore.model.Product
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ProductMapperImpl(
    @Autowired
    private val sqlSession: SqlSession
): ProductMapper {


    private val mapper = sqlSession.getMapper(ProductMapper::class.java)

    override fun getProductListByCategory(categoryId: String): List<Product> {
        return mapper.getProductListByCategory(categoryId)
    }

    override fun getProduct(productId: String): Product? {
        return mapper.getProduct(productId)
    }

    override fun searchProductList(keywords: String): List<Product> {
        return mapper.searchProductList(keywords)
    }
}