package com.seongjae.secureorder.repository

import com.seongjae.secureorder.model.User
import com.seongjae.secureorder.service.UserService
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val userService : UserService
) {

    // 더미 유저 데이터 설정
    val users = listOf(
        User(
            id = 1,
            name = "Bae Seongjae",
            dob = "19920923",
            gender = "Male",
            residence = "Seoul",
            email = "seongjae@example.com",
            password = userService.encodePassword("password123"), // 해시된 비밀번호
            roles = listOf("USER")
        )
    )


    // 이름으로 사용자 찾기
    fun findByName(name: String): User? {
        return users.find { it.name == name }
    }

    // 이메일을 통해 유저를 찾는 함수
    fun findByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    // 사용자 저장
    fun save(user: User) {
        users.toMutableList().also { it.add(user) }
    }
}