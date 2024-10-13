package com.seongjae.secureorder.controller

import com.seongjae.secureorder.repository.UserRepository
import com.seongjae.secureorder.service.ImageProcessingService
import com.seongjae.secureorder.service.UserService
import com.seongjae.secureorder.utils.JwtUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val jwtUtils: JwtUtils,
    private val imageProcessingService: ImageProcessingService
) {

    // 이메일과 비밀번호를 통한 로그인과 JWT 발급
    @PostMapping("/login")
    fun login(
        @RequestParam email: String,
        @RequestParam password: String
    ): ResponseEntity<Any> {
        // 이메일로 사용자 검색
        val user = userRepository.findByEmail(email)

        return if (user != null && userService.checkPassword(password, user.password)) {
            // 비밀번호가 일치하면 성공 메시지 반환
            ResponseEntity.ok("Login successful!")
        } else {
            // 비밀번호 또는 이메일이 틀리면 에러 메시지 반환
            ResponseEntity("Invalid credentials", HttpStatus.UNAUTHORIZED)
        }
    }

    // 얼굴 인증을 통한 로그인 (얼굴이 일치하면 JWT 발급)
    @PostMapping("/face-login")
    fun faceLogin(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        if (file.isEmpty) {
            return ResponseEntity("Please upload a valid image", HttpStatus.BAD_REQUEST)
        }

        // 얼굴 인식 결과
        val result = imageProcessingService.processImage(file)

        // 얼굴이 1개 이상 감지된 경우만 처리 (추가 로직 필요)
        return if (result.contains("1 face")) {
            // 사용자가 존재한다고 가정하고 JWT 발급
            val user = userRepository.findByEmail("seongjae@example.com") // 실제로는 얼굴 정보를 기반으로 이메일을 찾는 로직 필요
            if (user != null) {
                val jwt = jwtUtils.generateToken(user.email)
                ResponseEntity.ok(mapOf("token" to jwt))
            } else {
                ResponseEntity("User not found", HttpStatus.UNAUTHORIZED)
            }
        } else {
            ResponseEntity("Face recognition failed", HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestParam refreshToken: String): ResponseEntity<Any> {
        val email = jwtUtils.extractEmailFromRefreshToken(refreshToken)
        val user = userRepository.findByEmail(email)

        return if (user != null && jwtUtils.validateRefreshToken(refreshToken, user.email)) {
            val newToken = jwtUtils.generateToken(user.email)
            ResponseEntity.ok(mapOf("token" to newToken))
        } else {
            ResponseEntity("Invalid refresh token", HttpStatus.UNAUTHORIZED)
        }
    }
}