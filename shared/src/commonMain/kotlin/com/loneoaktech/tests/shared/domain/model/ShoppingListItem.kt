package com.loneoaktech.tests.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListItem(
    val name: String,
    val quantity: Int,
    val description: String? = null
)