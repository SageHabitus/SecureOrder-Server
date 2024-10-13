package com.seongjae.secureorder.controller

import com.seongjae.secureorder.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/place")
    fun placeOrder(
        @RequestParam userId: Int,
        @RequestParam menuId: String,
        @RequestParam quantity: Int
    ): ResponseEntity<Any> {
        val order = orderService.placeOrder(userId, menuId, quantity)
        return ResponseEntity.ok(order)
    }

    @GetMapping("/{userId}")
    fun getUserOrders(@PathVariable userId: Int): ResponseEntity<List<Any>> {
        val orders = orderService.getOrdersByUserId(userId)
        return ResponseEntity.ok(orders)
    }
}
