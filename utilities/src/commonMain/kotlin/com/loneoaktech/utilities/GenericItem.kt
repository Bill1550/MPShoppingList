package com.loneoaktech.utilities

import kotlinx.serialization.Serializable

@Serializable
data class GenericItem(
    val id: Long,
    val name: String,
    val description: String? = null
)