package com.seongjae.secureorder.config

import com.seongjae.secureorder.utils.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtils: JwtUtils
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**").permitAll() // 로그인, 회원가입은 누구나 접근 가능
            .anyRequest().authenticated() // 그 외는 인증 필요
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
            .csrf().disable() // CSRF 비활성화
        return http.build()
    }

    class JwtAuthenticationFilter(private val jwtUtils: JwtUtils) : OncePerRequestFilter() {
        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
        ) {
            val token = request.getHeader("Authorization")?.substring(7)
            if (token != null && jwtUtils.validateToken(token, jwtUtils.extractEmail(token))) {
                val auth = UsernamePasswordAuthenticationToken(jwtUtils.extractEmail(token), null, emptyList())
                auth.isAuthenticated = true
                SecurityContextHolder.getContext().authentication = auth
            }
            filterChain.doFilter(request, response)
        }
    }
}