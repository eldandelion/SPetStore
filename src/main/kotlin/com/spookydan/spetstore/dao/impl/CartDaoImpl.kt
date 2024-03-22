package com.spookydan.spetstore.dao.impl

import com.spookydan.spetstore.dao.CartDao
import com.spookydan.spetstore.dao.DBUtil
import com.spookydan.spetstore.dao.ItemDao
import com.spookydan.spetstore.model.CartItem
import com.spookydan.spetstore.model.Item
import com.spookydan.spetstore.model.Product
import org.springframework.stereotype.Repository

@Repository
class CartDaoImpl : CartDao {
    private val itemDao: ItemDao = ItemDaoImpl()

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

    override fun insertCartItem(cartItem: CartItem, userid: String) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(insertCartItemString)

            preparedStatement?.apply {
                setString(1, cartItem.item.itemId)
                setInt(2, cartItem.quantity)
                setString(3, userid)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItem(itemId: String): Item? {
        var item: Item? = null
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(getItemString)

            preparedStatement?.apply {
                setString(1, itemId)
                val resultSet = executeQuery()
                if (resultSet.next()) {
                    item = Item()
                    item?.apply {
                        this.itemId = resultSet.getString(1)
                        listPrice = resultSet.getBigDecimal(2)
                        unitCost = resultSet.getBigDecimal(3)
                        supplierId = resultSet.getInt(4)
                        val product = Product()
                        product.productId = resultSet.getString(5)
                        product.name = resultSet.getString(6)
                        product.description = resultSet.getString(7)
                        product.categoryId = resultSet.getString(8)
                        this.product = product
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

    override fun getCartItemListBy(userid: String): List<CartItem> {
        val cartItemList: MutableList<CartItem> = ArrayList()
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(getCartItemListByUserid)
            preparedStatement?.apply {

                setString(1, userid)
                val resultSet = executeQuery()
                while (resultSet.next()) {
                    val cartItem = CartItem()
                    val itemId = resultSet.getString(1)
                    val item = itemDao.getItem(itemId)
                    item?.let {
                        cartItem.item = it
                    }

                    val quantity = resultSet.getInt(2)
                    cartItem.quantity = quantity
                    cartItem.calculateTotal()
                    cartItem.inStock = itemDao.getInventoryQuantity(itemId) > 0
                    cartItemList.add(cartItem)
                }
                DBUtil.closeResultSet(resultSet)
                DBUtil.closePreparedStatement(this)


            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cartItemList
    }

    override fun incrementQuantity(userId: String, itemId: String) {
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(incrementQuantityString)

            pStatement?.apply {
                setString(1, itemId)
                setString(2, userId)
                val res = executeUpdate()
                if (res == 1) {
                    println("incrementQuantity成功")
                } else {
                    println("incrementQuantity失败")
                }
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun decrementQuantity(userId: String, itemId: String) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(DECREMENT_QUANTITY)
            preparedStatement?.apply {
                setString(1, itemId)
                setString(2, userId)
                val res = executeUpdate()
                if (res == 1) {
                    println("decrementQuantity成功")
                } else {
                    println("decrementQuantity失败")
                }
                DBUtil.closePreparedStatement(this)
            }
            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun removeItemById(userId: String, cartItem: CartItem) {
        try {
            val connection = DBUtil.getConnection()
            val preparedStatement = connection?.prepareStatement(removeItemByIdString)
            preparedStatement?.apply {
                setString(1, cartItem.item.itemId)
                setString(2, userId)
                executeUpdate()
                DBUtil.closePreparedStatement(preparedStatement)
            }

            DBUtil.closeConnection(connection)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun removeItemById(userId: String, itemId: String) {
        try {
            val connection = DBUtil.getConnection()
            val pStatement = connection?.prepareStatement(removeItemByIdString)
            pStatement?.apply {
                setString(1, itemId)
                setString(2, userId)
                executeUpdate()
                DBUtil.closePreparedStatement(this)
            }

            DBUtil.closeConnection(connection)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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