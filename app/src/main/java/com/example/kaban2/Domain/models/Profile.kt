package com.example.kaban2.Domain.models

import kotlinx.serialization.SerialName

//import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val user_id:String?,
    val username:String,
    val surname:String,
    @SerialName("date_of_birth") //Аннотация указывает, что при сериализации/десериализации это свойство должно соответствовать ключу "datebirth" в формате данных (например, JSON)
    val dateBirth:String?
)
