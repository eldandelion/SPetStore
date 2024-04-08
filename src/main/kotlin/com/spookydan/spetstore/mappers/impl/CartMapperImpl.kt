package com.spookydan.spetstore.mappers.impl

import com.spookydan.spetstore.mappers.CartMapper
import com.spookydan.spetstore.mappers.DBUtil
import com.spookydan.spetstore.mappers.ItemMapper
import com.spookydan.spetstore.model.CartItem
import com.spookydan.spetstore.model.Item
import org.apache.ibatis.session.SqlSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.logging.Logger

@Repository
class CartMapperImpl(
    @Autowired
    private val itemMapper: ItemMapper,
    @Autowired
    private val sqlSession: SqlSession
) : CartMapper {


    private companion object {
        private const val insertCartItemString = "INSERT INTO cart (itemid, quantity, userid) VALUES (?, ?, ?)"
        private const val getItemString = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId," +
                "I.PRODUCTID AS productId,NAME AS productName," +
                "DESCN AS productDescription,CATEGORY AS CategoryId,STATUS," +
                "ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3," +
                "ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity " +
                "from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID=?"
        private const val getCartItemListByUserid = "SELECT itemid, quantity FROM cart WHERE userid = ?"
        private const val incrementQuantityString = "UPDATE cart SET quantity = quantity + 1 WHERE itemid = ? AND userid = ?"
        private const val DECREMENT_QUANTITY = "UPDATE cart SET quantity = quantity - 1 WHERE itemid = ? AND userid = ?"
        private const val removeItemByIdString = "DELETE FROM cart WHERE itemid = ? AND userid = ?"
        private const val updateQuantityByItemIdString = "UPDATE cart SET quantity = ? WHERE itemid = ? AND userid = ?"
        private const val removeAllCartItemsByUseridString = "DELETE FROM cart WHERE userid = ?"
    }

    override fun insertCartItem(cartItem: CartItem, userId: String) {
        try {
            val mapper = sqlSession.getMapper(CartMapper::class.java)
            mapper.insertCartItem(cartItem, userId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItem(itemId: String): Item? {
        var item: Item? = null
        try {
            val mapper = sqlSession.getMapper(ItemMapper::class.java)
            item = mapper.getItem(itemId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return item
    }

    private val logger = Logger.getLogger("CartMapper")

    override fun getCartItemListBy(userid: String): List<CartItem> {
        val cartItemList: MutableList<CartItem> = ArrayList()
        try {
            val mapper = sqlSession.getMapper(CartMapper::class.java)
            val resultSet = mapper.getCartItemListBy(userid)

            resultSet.forEach {
                logger.info("quantity is ${it.quantity} and price is ${it.item.listPrice} and total is ${it.total}" )
            }

            cartItemList.addAll(resultSet)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cartItemList
    }

    override fun incrementQuantity(userId: String, itemId: String) {
        try {
            val mapper = sqlSession.getMapper(CartMapper::class.java)

            mapper.incrementQuantity(userId, itemId)

            println("incrementQuantity成功")
        } catch (e: Exception) {
            println("incrementQuantity失败")
            e.printStackTrace()
        }
    }

    override fun decrementQuantity(userId: String, itemId: String) {
        try {
            val mapper = sqlSession.getMapper(CartMapper::class.java)

            mapper.decrementQuantity(userId, itemId)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun removeItemById(userId: String, itemId: String) {
        try {
            val mapper = sqlSession.getMapper(CartMapper::class.java)

            mapper.removeItemById(userId, itemId)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //TODO gotta finish these but I am too lazy to do that
    //besides, they are never used in the code, so...
    override fun updateQuantity(userId: String, cartItem: CartItem, quantity: Int) {
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(updateQuantityByItemIdString)
            pStatement?.apply {
                setInt(1, quantity)
                setString(2, cartItem.item.itemId)
                setString(3, userId)
                val res = executeUpdate()
                if (res == 1) {
                    println("updateQuantityByItemId成功")
                } else {
                    println("updateQuantityByItemId失败")
                }
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    override fun removeAllCartItemsBy(userid: String) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(removeAllCartItemsByUseridString)
            preparedStatement?.apply {
                setString(1, userid)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }
            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}