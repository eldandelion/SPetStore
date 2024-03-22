package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.LineItemDao
import com.spookydan.spetstore.dao.OrderDao
import com.spookydan.spetstore.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.sql.Date
import java.sql.Statement

@Repository("OrderDao")

class OrderDaoImpl(
    @Autowired
    private val lineItemDao: LineItemDao
) : OrderDao {

    companion object {
        private const val GET_ORDERS_BY_USERNAME =
            "SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS, SUBTOTAL FROM ORDERS, ORDERSTATUS WHERE ORDERS.USERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE"
        private const val GET_ORDER =
            "SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS, SUBTOTAL FROM ORDERS, ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID"
        private const val INSERT_ORDER =
            "INSERT INTO ORDERS (USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE,BILLZIP, BILLCOUNTRY,COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,CREDITCARD, EXPRDATE, CARDTYPE, LOCALE, SUBTOTAL) VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?)"
        private const val INSERT_ORDER_STATUS =
            "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)  VALUES (?, ?, ?, ?)"
    }


    override fun getOrdersByUsername(username: String): List<Order> {
        val list: MutableList<Order> = java.util.ArrayList()
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(GET_ORDERS_BY_USERNAME)
            preparedStatement?.let {
                it.setString(1, username)
                val resultSet = it.executeQuery()
                while (resultSet.next()) {
                    val order = Order().apply {
                        billAddress1 = resultSet.getString(1)
                        billAddress2 = resultSet.getString(2)
                        billCity = resultSet.getString(3)
                        billCountry = resultSet.getString(4)
                        billState = resultSet.getString(5)
                        billToFirstName = resultSet.getString(6)
                        billToFirstName = resultSet.getString(6)
                        billToLastName = resultSet.getString(7)
                        billZip = resultSet.getString(8)
                        shipAddress1 = resultSet.getString(9)
                        shipAddress2 = resultSet.getString(10)
                        shipCity = resultSet.getString(11)
                        shipCountry = resultSet.getString(12)
                        shipState = resultSet.getString(13)
                        shipToFirstName = resultSet.getString(14)
                        shipToLastName = resultSet.getString(15)
                        shipZip = resultSet.getString(16)
                        cardType = resultSet.getString(17)
                        courier = resultSet.getString(18)
                        creditCard = resultSet.getString(19)
                        expiryDate = resultSet.getString(20)
                        locale = resultSet.getString(21)
                        orderDate = resultSet.getDate(22)
                        orderId = resultSet.getInt(23)
                        totalPrice = resultSet.getBigDecimal(24)
                        this.username = resultSet.getString(25)
                        status = resultSet.getString(26)
                        subTotal = resultSet.getBigDecimal(27)
                        lineItems = lineItemDao.getLineItemsByOrderId(orderId)
                    }
                    list.add(order)
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(it)
            }

            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return list
    }

    override fun getOrder(orderId: Int): Order? {
        var order: Order? = null
        try {
            val connection: Connection? = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(GET_ORDER)
            preparedStatement?.let {
                it.setInt(1, orderId)
                val resultSet = it.executeQuery()
                if (resultSet.next()) {
                    order = Order().apply {
                        billAddress1 = resultSet.getString(1)
                        billAddress2 = resultSet.getString(2)
                        billCity = resultSet.getString(3)
                        billCountry = resultSet.getString(4)
                        billState = resultSet.getString(5)
                        billToFirstName = resultSet.getString(6)
                        billToFirstName = resultSet.getString(6)
                        billToLastName = resultSet.getString(7)
                        billZip = resultSet.getString(8)
                        shipAddress1 = resultSet.getString(9)
                        shipAddress2 = resultSet.getString(10)
                        shipCity = resultSet.getString(11)
                        shipCountry = resultSet.getString(12)
                        shipState = resultSet.getString(13)
                        shipToFirstName = resultSet.getString(14)
                        shipToLastName = resultSet.getString(15)
                        shipZip = resultSet.getString(16)
                        cardType = resultSet.getString(17)
                        courier = resultSet.getString(18)
                        creditCard = resultSet.getString(19)
                        expiryDate = resultSet.getString(20)
                        locale = resultSet.getString(21)
                        orderDate = resultSet.getDate(22)
                        this.orderId = resultSet.getInt(23)
                        totalPrice = resultSet.getBigDecimal(24)
                        username = resultSet.getString(25)
                        status = resultSet.getString(26)
                        subTotal = resultSet.getBigDecimal(27)
                        lineItems = lineItemDao.getLineItemsByOrderId(orderId)
                    }
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(it)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return order
    }

    override fun insertOrder(order: Order) {
        try {
            val connection: Connection? = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)
            preparedStatement?.apply {
                setString(1, order.username)
                setDate(2, Date(order.orderDate.time))
                setString(3, order.shipAddress1)
                setString(4, order.shipAddress2)
                setString(5, order.shipCity)
                setString(6, order.shipState)
                setString(7, order.shipZip)
                setString(8, order.shipCountry)
                setString(9, order.billAddress1)
                setString(10, order.billAddress2)
                setString(11, order.billCity)
                setString(12, order.billState)
                setString(13, order.billZip)
                setString(14, order.billCountry)
                setString(15, order.courier)
                setBigDecimal(16, order.totalPrice)
                setString(17, order.billToFirstName)
                setString(18, order.billToLastName)
                setString(19, order.shipToFirstName)
                setString(20, order.shipToLastName)
                setString(21, order.creditCard)
                setString(22, order.expiryDate)
                setString(23, order.cardType)
                setString(24, order.locale)
                setBigDecimal(25, order.subTotal)

                executeUpdate()

                // Retrieve the generated order ID
                val generatedKeys = generatedKeys

                if (generatedKeys.next()) {
                    val generatedOrderId = generatedKeys.getLong(1)
                    order.orderId = generatedOrderId.toInt()
                    order.lineItems.forEach {
                        it.orderId = generatedOrderId.toInt()
                        lineItemDao.insertLineItem(it)
                    }
                }
                DBUtil.closePreparedStatement(this)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun insertOrderStatus(order: Order) {
        try {
            val connection: Connection? = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(INSERT_ORDER_STATUS)
            preparedStatement?.apply {
                setInt(1, order.orderId)
                setInt(2, order.lineItems.size)
                setDate(3, Date(order.orderDate.time))
                setString(4, order.status)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}