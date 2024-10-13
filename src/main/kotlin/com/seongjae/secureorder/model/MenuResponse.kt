package com.seongjae.secureorder.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MenuResponse(
    @JsonProperty("meals")
    val menus : List<Menu> = emptyList()
)

data class Menu(
    @JsonProperty("idMeal")
    val id: String?,
    @JsonProperty("strMeal")
    val name: String?,
    @JsonProperty("strCategory")
    val category: String?,
    @JsonProperty("strInstructions")
    val instructions: String?,
    @JsonProperty("strMealThumb")
    val thumbnail: String?
)
