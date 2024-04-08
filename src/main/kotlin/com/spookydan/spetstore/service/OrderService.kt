package com.spookydan.spetstore.service

import com.spookydan.spetstore.mappers.CartMapper
import com.spookydan.spetstore.mappers.ItemMapper
import com.spookydan.spetstore.mappers.OrderDao
import com.spookydan.spetstore.mappers.SequenceMapper
import com.spookydan.spetstore.model.Account
import com.spookydan.spetstore.model.Order
import com.spookydan.spetstore.model.Sequence
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class OrderService(
    @Autowired
    private val itemMapper: ItemMapper,
    @Autowired
    private val orderDao: OrderDao,
    @Autowired
    private val sequenceMapper: SequenceMapper,
    @Autowired
    private val cartMapper: CartMapper
) {


    fun initOrder(account: Account, subTotal: BigDecimal) : Order {

        return Order().apply {
            username = account.username
            orderDate = Date()
            shipToFirstName = account.firstName
            shipToLastName = account.lastName
            shipAddress1 = account.address1
            shipAddress2 = account.address2
            shipCity = account.city
            shipState = account.state
            shipZip = account.zip
            shipCountry = account.country

            billToFirstName = account.firstName
            billToLastName = account.lastName
            billAddress1 = account.address1
            billAddress2 = account.address2
            billCity = account.city
            billState = account.state
            billZip = account.zip
            billCountry = account.country

            totalPrice = BigDecimal(0)

            creditCard = "999 9999 9999 9999"
            expiryDate = "12/03"
            cardType = "Visa"
            courier = "UPS"
            locale = "CA"
            status = "P"

            this.subTotal = subTotal


            val iterator = cartMapper.getCartItemListBy(account.username).iterator()

            while (iterator.hasNext()) {
                val cartItem = iterator.next()
                addLineItem(cartItem)
            }
        }
    }

    fun getNextId(name: String): Int {
        var sequence = Sequence(name, -1)
        sequence = sequenceMapper.getSequence(sequence)
        val parameterObject = Sequence(name, sequence.getNextId() + 1)
        sequenceMapper.updateSequence(parameterObject)
        return sequence.getNextId()
    }

    fun insertOrder(order: Order) {
        orderDao.insertOrder(order)


    }

    fun getOrder(orderId: Int): Order? {
        return orderDao.getOrder(orderId)
    }

    fun getOrdersByUsername(username: String): List<Order> {
        return orderDao.getOrdersByUsername(username)
    }
}
