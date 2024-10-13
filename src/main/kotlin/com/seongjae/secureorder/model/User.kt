package com.seongjae.secureorder.model

data class User(
    val id: Int?,
    val name: String,
    val email: String,
    val password: String,
    val dob: String,      // 생년월일
    val gender: String,   // 성별
    val residence: String, // 거주지
    val roles: List<String>
)