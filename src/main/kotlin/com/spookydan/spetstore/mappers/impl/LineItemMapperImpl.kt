package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.LineItemMapper
import com.spookydan.spetstore.model.LineItem
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class LineItemMapperImpl(
    @Autowired
    private val sqlSession: SqlSession,
) : LineItemMapper {


    override fun getLineItemsByOrderId(orderId: Int): List<LineItem> {
        val lineItemList = ArrayList<LineItem>()
        try {
            val lineItemMapper = sqlSession.getMapper(LineItemMapper::class.java)

            lineItemList.addAll(lineItemMapper.getLineItemsByOrderId(orderId))

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lineItemList
    }

    override fun insertLineItems(lineItems: List<LineItem>) {
        try {
            val mapper = sqlSession.getMapper(LineItemMapper::class.java)

            mapper.insertLineItems(lineItems)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}