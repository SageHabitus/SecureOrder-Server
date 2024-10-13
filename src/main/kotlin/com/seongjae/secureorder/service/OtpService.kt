package com.seongjae.secureorder.service

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OtpService {

    private val otpStorage = mutableMapOf<String, String>() // 이메일 -> OTP

    // OTP 생성
    fun generateOtp(email: String): String {
        val otp = Random.nextInt(100000, 999999).toString()
        otpStorage[email] = otp
        return otp
    }

    // OTP 검증
    fun validateOtp(email: String, otp: String): Boolean {
        return otpStorage[email] == otp
    }
}
