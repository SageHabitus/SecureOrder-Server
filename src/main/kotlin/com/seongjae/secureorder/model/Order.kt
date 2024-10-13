package com.seongjae.secureorder.model

import org.springframework.stereotype.Service

data class Order(
    val orderId: Int,
    val userId: Int,
    val menuId: String,
    val quantity: Int
)
