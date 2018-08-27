package uz.sesh.flex.data.datasource.mapper

import uz.sesh.flex.data.datasource.models.FlexResponse
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.domain.model.User

class FlexMapper {
    companion object {
        fun map(flexesResponse: ArrayList<FlexResponse>): ArrayList<Flex> {
            val flexes: ArrayList<Flex> = ArrayList()
            for (flex in flexesResponse) {
                flexes.add(map(flex))
            }
            return flexes
        }

        fun map(flexResponse: FlexResponse): Flex {
            val title = flexResponse.title
            val description = flexResponse.description
            val friendsCount = flexResponse.friends_count
            val membersCount = flexResponse.members_count
            val flexOwner = flexResponse.owner
            val owner: User = User(
                    flexOwner.firstName,
                    flexOwner.lastName,
                    flexOwner.username,
                    flexOwner.bio,
                    null
            )
            return Flex(
                    title,
                    description,
                    friendsCount,
                    membersCount,
                    owner
            )
        }
    }

}