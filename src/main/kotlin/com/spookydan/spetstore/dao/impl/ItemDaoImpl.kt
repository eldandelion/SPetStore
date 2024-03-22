package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.ItemDao
import com.spookydan.spetstore.model.Enums
import com.spookydan.spetstore.model.Item
import com.spookydan.spetstore.model.Product
import org.springframework.stereotype.Repository

@Repository("itemDao")
class ItemDaoImpl : ItemDao {

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


    override fun updateInventoryQuantity(param: Map<String, Any>) {
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(UPDATE_INVENTORY_QUANTITY)
            pStatement?.apply {
                    val itemId = param.keys.iterator().next()
                    val increment = param[itemId] as Int
                    setInt(1, increment)
                    setString(2, itemId)
                    executeUpdate()
                    DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
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
        val itemList = ArrayList<Item>()
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(GET_ITEM_LIST_BY_PRODUCT_ID)
            pStatement?.apply {
                    setString(1, productId)
                    val resultSet = executeQuery()
                    while (resultSet.next()) {
                        val item = Item().apply {
                            itemId = resultSet.getString(1)
                            listPrice = resultSet.getBigDecimal(2)
                            unitCost = resultSet.getBigDecimal(3)
                            supplierId = resultSet.getInt(4)
                            product = Product().apply {
                                this.productId = resultSet.getString(5)
                                name = resultSet.getString(6)
                                description = resultSet.getString(7)
                                categoryId = resultSet.getString(8)
                            }
                            status = resultSet.getString(9)
                            attribute1 = resultSet.getString(10)
                            attribute2 = resultSet.getString(11)
                        }
                        itemList.add(item)
                    }
                    DBUtil.closeResultSet(resultSet)
                    DBUtil.closePreparedStatement(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return itemList
    }

    override fun getItem(itemId: String): Item? {
        var item: Item? = null
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(GET_ITEM)
            pStatement?.apply {
                setString(1, itemId)
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    item = Item()
                    item?.apply {
                        this.itemId = resultSet.getString(1)
                        listPrice = resultSet.getBigDecimal(2)
                        unitCost = resultSet.getBigDecimal(3)
                        supplierId = resultSet.getInt(4)
                        product = Product().apply {
                            this.productId = resultSet.getString(5)
                            name = resultSet.getString(6)
                            description = resultSet.getString(7)
                            categoryId = resultSet.getString(8)
                        }
                        status = resultSet.getString(9)
                        attribute1 = resultSet.getString(10)
                        attribute2 = resultSet.getString(11)
                        quantity = resultSet.getInt(15)
                    }
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return item
    }
}