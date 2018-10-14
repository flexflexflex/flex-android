package uz.sesh.flex.domain.model

import java.io.Serializable

data class Flex(
        var id:Int?,
        val title: String?,
        val description: String?,
        val image: String?,
        val friendsCount: Int?,
        val membersCount: Int?,
        val owner: User?):Serializable