package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.CategoryDao
import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.model.Category
import org.springframework.stereotype.Repository

@Repository("categoryDao")
class CategoryDaoImpl : CategoryDao {
    private val getCategoryListString =
        "SELECT CATID AS categoryId, NAME,DESCN AS description FROM CATEGORY"

    private val getCategoryString =
        "SELECT CATID AS categoryId, NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?"

    override fun getCategoryList(): List<Category> {
        val categoryList = ArrayList<Category>()
        try {
            val connection = DBUtil.getConnection()
            val statement = connection?.createStatement()
            statement?.let {
                val resultSet = it.executeQuery(getCategoryListString)
                while (resultSet.next()) {
                    categoryList.add(
                        Category().apply {
                            categoryId = resultSet.getString("categoryId")
                            name = resultSet.getString("NAME")
                            description = resultSet.getString("description")
                        }
                    )
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closeStatement(it)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return categoryList
    }

    override fun getCategory(categoryId: String): Category? {
        var category: Category? = null
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(getCategoryString)
            preparedStatement?.apply {
                setString(1, categoryId)
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    category = Category().apply {
                        this.categoryId = resultSet.getString("categoryId")
                        name = resultSet.getString("NAME")
                        description = resultSet.getString("description")
                    }
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return category
    }
}