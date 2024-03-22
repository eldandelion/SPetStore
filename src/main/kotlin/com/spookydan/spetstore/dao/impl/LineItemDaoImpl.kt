package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.ItemDao
import com.spookydan.spetstore.dao.LineItemDao
import com.spookydan.spetstore.model.LineItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class LineItemDaoImpl(
    @Autowired
    private val itemDao: ItemDao
) : LineItemDao {

    companion object {
        private const val getLineItemsByOrderId =
            "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?"
        private const val insertLineItem =
            "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)"

    }

    override fun getLineItemsByOrderId(orderId: Int): List<LineItem> {
        val lineItemList = ArrayList<LineItem>()
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(getLineItemsByOrderId)
            preparedStatement?.apply {
                setInt(1, orderId)
                val resultSet = executeQuery()
                while (resultSet.next()) {
                    lineItemList.add(
                        LineItem().apply {
                            this.orderId = resultSet.getInt(1)
                            lineNumber = resultSet.getInt(2)
                            itemId = resultSet.getString(3)
                            quantity = resultSet.getInt(4)
                            unitPrice = resultSet.getBigDecimal(5)
                            item = itemDao.getItem(itemId)
                            calculateTotal()
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
        return lineItemList
    }

    override fun insertLineItem(lineItem: LineItem) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(insertLineItem)
            preparedStatement?.apply {
                    setInt(1, lineItem.orderId)
                    setInt(2, lineItem.lineNumber)
                    setString(3, lineItem.itemId)
                    setInt(4, lineItem.quantity)
                    setBigDecimal(5, lineItem.unitPrice)
                    executeUpdate()
                    DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}