package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.ProductDao
import com.spookydan.spetstore.model.Product
import org.springframework.stereotype.Repository
import java.sql.Connection
@Repository("productDao")
class ProductDaoImpl : ProductDao {

    companion object {
        private const val GET_PRODUCT_LIST =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY=?"
        private const val GET_PRODUCT =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?"
        private const val SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?"
    }

    override fun getProductListByCategory(categoryId: String): List<Product> {
        val products: MutableList<Product> = ArrayList()
        try {
            val connection: Connection? = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(GET_PRODUCT_LIST)
            pStatement?.apply {
                setString(1, categoryId)
                val resultSet = executeQuery()
                while (resultSet.next()) {
                    products.add(
                        Product().apply {
                            productId = resultSet.getString(1)
                            name = resultSet.getString(2)
                            description = resultSet.getString(3)
                            this.categoryId = resultSet.getString(4)
                        }
                    )
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return products
    }

    override fun getProduct(productId: String): Product? {
        var product: Product? = null
        try {
            val connection: Connection? = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(GET_PRODUCT)
            pStatement?.apply {
                setString(1, productId)
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    product = Product().apply {
                        this.productId = resultSet.getString(1)
                        name = resultSet.getString(2)
                        description = resultSet.getString(3)
                        categoryId = resultSet.getString(4)
                    }
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return product
    }

    override fun searchProductList(keywords: String): List<Product> {
        val productList: MutableList<Product> = ArrayList()
        try {
            val connection: Connection? = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(SEARCH_PRODUCT_LIST)
            pStatement?.apply {
                setString(1, keywords)
                val resultSet = executeQuery()
                while (resultSet.next()) {
                    productList.add(
                        Product().apply {
                            productId = resultSet.getString(1)
                            name = resultSet.getString(2)
                            description = resultSet.getString(3)
                            categoryId = resultSet.getString(4)
                        }
                    )
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return productList
    }
}