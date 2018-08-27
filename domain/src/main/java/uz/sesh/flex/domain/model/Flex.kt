package uz.sesh.flex.domain.model

data class Flex(
        val title:String,
        val description:String,
        val friendsCount: Int,
        val membersCount: Int,
        val owner:User)