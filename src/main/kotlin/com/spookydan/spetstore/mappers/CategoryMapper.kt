package com.spookydan.spetstore.mappers

import com.spookydan.spetstore.model.Category

interface CategoryMapper {
    fun getCategoryList(): List<Category>

    fun getCategory(categoryId: String): Category?
}
