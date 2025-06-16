package com.example.kaban2.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Services (

    val name:String,
    val description:String,
    val term:String,
    val cost:Int,

)