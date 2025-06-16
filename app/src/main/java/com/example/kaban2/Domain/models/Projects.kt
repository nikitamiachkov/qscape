package com.example.kaban2.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Projects (

    val user_id:String?,
    val service_id: Int,
    val status:String?,
    val completeness: Float?

    )