package com.seongjae.secureorder.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    private val passwordEncoder = BCryptPasswordEncoder()

    // 비밀번호 해싱
    fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    // 비밀번호 검증 (로그인 시)
    fun checkPassword(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }
}
