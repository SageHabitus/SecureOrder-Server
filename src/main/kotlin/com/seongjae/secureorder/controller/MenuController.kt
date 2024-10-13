package com.seongjae.secureorder.controller

import com.seongjae.secureorder.service.MenuService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuService: MenuService
) {

    private val categories = listOf("Seafood", "Dessert", "Pasta") // 카테고리 리스트

    suspend fun getCategories(): List<String> {
        return categories
    }

    @GetMapping
    suspend fun getMenusByCategory(@RequestParam category: String): ResponseEntity<Any> {
        val menus = menuService.getMenusByCategory(category)
        return ResponseEntity.ok(menus)
    }
}
