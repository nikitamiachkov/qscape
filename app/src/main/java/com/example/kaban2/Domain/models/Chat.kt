package com.example.kaban2.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Chat (

    val user_id:String?,
    var from: Boolean,
    val message:String?,

    )