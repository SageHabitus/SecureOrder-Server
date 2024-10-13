package com.seongjae.secureorder.controller

import com.seongjae.secureorder.service.OtpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class OtpController(
    private val otpService: OtpService
) {

    @PostMapping("/send-otp")
    fun sendOtp(@RequestParam email: String): ResponseEntity<String> {
        val otp = otpService.generateOtp(email)
        // 실제로 이메일이나 SMS로 OTP를 발송하는 로직이 필요함
        return ResponseEntity.ok("OTP has been sent to $email")
    }

    @PostMapping("/verify-otp")
    fun verifyOtp(@RequestParam email: String, @RequestParam otp: String): ResponseEntity<String> {
        val isValid = otpService.validateOtp(email, otp)
        return if (isValid) {
            ResponseEntity.ok("OTP is valid")
        } else {
            ResponseEntity.badRequest().body("Invalid OTP")
        }
    }
}

