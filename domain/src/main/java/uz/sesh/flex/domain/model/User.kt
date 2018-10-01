package uz.sesh.flex.domain.model

import java.io.Serializable

data class User(val firstName:String? = null,
                val lastName: String? = null,
                val username:String? = null,
                val bio:String? = null,
                val phone:String? = null):Serializable