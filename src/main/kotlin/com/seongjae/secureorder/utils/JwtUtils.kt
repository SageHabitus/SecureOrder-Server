package com.seongjae.secureorder.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {

    private val secretKey = "mySecretKey"
    private val refreshTokenSecretKey = "myRefreshSecretKey" // 리프레시 토큰을 위한 별도 키
    private val expirationTime = 1000 * 60 * 60 * 10 // 10시간 유효
    private val refreshTokenExpirationTime = 1000 * 60 * 60 * 24 * 7 // 7일 유효

    // JWT 토큰 생성
    fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    // 리프레시 토큰 생성
    fun generateRefreshToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + refreshTokenExpirationTime))
            .signWith(SignatureAlgorithm.HS256, refreshTokenSecretKey)
            .compact()
    }

    // JWT 토큰에서 이메일 추출
    fun extractEmail(token: String): String {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .subject
    }

    // 리프레시 토큰에서 이메일 추출
    fun extractEmailFromRefreshToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(refreshTokenSecretKey)
            .parseClaimsJws(token)
            .body
            .subject
    }

    // JWT 토큰 유효성 검증
    fun validateToken(token: String, email: String): Boolean {
        val extractedEmail = extractEmail(token)
        return extractedEmail == email && !isTokenExpired(token)
    }

    // 리프레시 토큰 유효성 검증
    fun validateRefreshToken(token: String, email: String): Boolean {
        val extractedEmail = extractEmailFromRefreshToken(token)
        return extractedEmail == email && !isTokenExpired(token)
    }

    // 토큰 만료 여부 확인
    private fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
            .expiration
        return expiration.before(Date())
    }
}