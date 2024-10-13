package com.seongjae.secureorder.controller

import com.seongjae.secureorder.model.User
import com.seongjae.secureorder.repository.UserRepository
import com.seongjae.secureorder.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    // 회원가입 API
    @PostMapping("/signup")
    fun signUp(
        @RequestParam name: String,
        @RequestParam email: String,
        @RequestParam password: String,
        @RequestParam dob: String,  // 필수 값 추가
        @RequestParam gender: String,  // 필수 값 추가
        @RequestParam residence: String  // 필수 값 추가
    ): ResponseEntity<String> {
        // 비밀번호 해싱
        val encodedPassword = userService.encodePassword(password)

        // 사용자 정보 저장
        val user = User(
            id = null, // 자동 증가
            name = name,
            email = email,
            password = encodedPassword,
            dob = dob,
            gender = gender,
            residence = residence,
            roles = listOf("USER")
        )

        userRepository.save(user)
        return ResponseEntity("User registered successfully", HttpStatus.OK)
    }
}

