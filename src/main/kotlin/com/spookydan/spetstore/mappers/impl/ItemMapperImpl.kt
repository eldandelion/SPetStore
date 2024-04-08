package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.DBUtil
import com.spookydan.spetstore.mappers.ItemMapper
import com.spookydan.spetstore.model.Enums
import com.spookydan.spetstore.model.Item
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("itemDao")
class ItemMapperImpl(
    @Autowired
    private val sqlSession: SqlSession
) : ItemMapper {

    companion object {
        private const val GET_ITEM_LIST_BY_PRODUCT_ID =
            "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS categoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?"

        private const val GET_ITEM =
            "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS CategoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID=?"

        private const val GET_INVENTORY_QUANTITY =
            "SELECT QTY AS QUANTITY FROM INVENTORY WHERE ITEMID = ?"

        private const val UPDATE_INVENTORY_QUANTITY =
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?"
    }


    private val mapper = sqlSession.getMapper(ItemMapper::class.java)

    override fun updateInventoryQuantity(param: Map<String, Any>) {
        try {
            mapper.updateInventoryQuantity(param)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getInventoryQuantity(itemId: String): Int {
        var result = Enums.OUT_OF_STOCK
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(GET_INVENTORY_QUANTITY)
            preparedStatement?.apply {
                setString(1, itemId)
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    result = resultSet.getInt(1)
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }
            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }


    override fun getItemListByProduct(productId: String): List<Item> {
        return mapper.getItemListByProduct(productId)
    }

    override fun getItem(itemId: String): Item? {
        var item: Item? = null
        try {
            item = mapper.getItem(itemId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return item
    }
}