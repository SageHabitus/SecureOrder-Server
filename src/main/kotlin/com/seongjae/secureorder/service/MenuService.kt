package com.seongjae.secureorder.service

import com.seongjae.secureorder.model.MenuResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class MenuService(
    private val webClient: WebClient.Builder
) {

    private val baseUrl = "https://www.themealdb.com/api/json/v1/1"

    suspend fun getMenusByCategory(category: String): MenuResponse {

        return webClient.build()
            .get()
            .uri("$baseUrl/filter.php?c=$category")
            .retrieve()
            .bodyToMono(MenuResponse::class.java)
            .awaitSingle()
            .also {
                println("API Response: $it")
            }
    }
}