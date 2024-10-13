package com.seongjae.secureorder.service

import com.seongjae.secureorder.model.Order
import org.springframework.stereotype.Service

@Service
class OrderService {

    private val orders = mutableListOf<Order>()

    // 주문 추가
    fun placeOrder(userId: Int, menuId: String, quantity: Int): Order {
        val order = Order(
            orderId = orders.size + 1,
            userId = userId,
            menuId = menuId,
            quantity = quantity
        )
        orders.add(order)
        return order
    }

    // 특정 사용자의 모든 주문 보기
    fun getOrdersByUserId(userId: Int): List<Order> {
        return orders.filter { it.userId == userId }
    }
}