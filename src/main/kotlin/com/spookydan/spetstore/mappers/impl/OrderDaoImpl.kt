package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.DBUtil
import com.spookydan.spetstore.mappers.LineItemMapper
import com.spookydan.spetstore.mappers.OrderDao
import com.spookydan.spetstore.model.Order
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.util.logging.Logger

@Repository("OrderDao")

class OrderDaoImpl(
    @Autowired
    private val sqlSession: SqlSession,
    @Autowired
    private val lineItemMapper: LineItemMapper
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

    private val logger = Logger.getLogger("Orders")

    override fun getOrdersByUsername(username: String): List<Order> {
        val list: MutableList<Order> = ArrayList()
        try {
            val orderMapper = sqlSession.getMapper(OrderDao::class.java)
            val orders = orderMapper.getOrdersByUsername(username)
            val lineItemMapper = sqlSession.getMapper(LineItemMapper::class.java)
            logger.info(lineItemMapper.getLineItemsByOrderId(22).size.toString())
            orders.forEach {
                it.lineItems = lineItemMapper.getLineItemsByOrderId(it.orderId)
            }
            list.addAll(orders)

        } catch (e: Exception) {
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
                        lineItems = lineItemMapper.getLineItemsByOrderId(orderId)
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
            val orderMapper = sqlSession.getMapper(OrderDao::class.java)
            orderMapper.insertOrder(order)
            orderMapper.insertOrderStatus(order, order.lineItems.size)
            val lineItemMapper = sqlSession.getMapper(LineItemMapper::class.java)
            order.lineItems.forEach {
                it.orderId = order.orderId
            }
            lineItemMapper.insertLineItems(order.lineItems)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun insertOrderStatus(order: Order, quantity: Int) {
        try {
            val orderMapper = sqlSession.getMapper(OrderDao::class.java)
            orderMapper.insertOrderStatus(order, quantity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    /* override fun insertOrderStatus(order: Order) {
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
    } */


}