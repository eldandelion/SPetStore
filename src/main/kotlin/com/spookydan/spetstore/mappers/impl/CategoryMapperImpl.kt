package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.CategoryMapper
import com.spookydan.spetstore.model.Category
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("categoryDao")
class CategoryMapperImpl(
    @Autowired
    private val sqlSession: SqlSession
) : CategoryMapper {

    override fun getCategoryList(): List<Category> {
        val categoryList = ArrayList<Category>()
        try {
            val mapper = sqlSession.getMapper(CategoryMapper::class.java)

            categoryList.addAll(mapper.getCategoryList())

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return categoryList
    }

    override fun getCategory(categoryId: String): Category? {
        var category: Category? = null
        try {
            val mapper = sqlSession.getMapper(CategoryMapper::class.java)

            category = mapper.getCategory(categoryId)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return category
    }
}