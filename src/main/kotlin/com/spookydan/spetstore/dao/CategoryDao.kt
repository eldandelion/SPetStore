package com.spookydan.spetstore.dao

import com.spookydan.spetstore.model.Category

interface CategoryDao {
    fun getCategoryList(): List<Category>

    fun getCategory(categoryId: String): Category?
}
