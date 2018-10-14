package uz.sesh.flex.data.datasource.mapper

import android.os.Parcel
import android.os.Parcelable
import uz.sesh.flex.data.datasource.models.flex.FlexResponse
import uz.sesh.flex.data.datasource.models.user.UserResponse
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.domain.model.User

class FlexMapper() : Mapper<FlexResponse, Flex>() {
    companion object {
        var instance = FlexMapper()
    }
    override fun reverseMap(value: Flex): FlexResponse {
        return FlexResponse(
                id = value.id,
                title = value.title,
                description = value.description,
                image = value.image,
                friends_count = value.friendsCount,
                members_count = value.membersCount,
                owner = UserMapper.instance.reverseMap(value.owner)
        )
    }

    override fun map(value: FlexResponse): Flex {
        val title = value.title
        val description = value.description
        val friendsCount = value.friends_count
        val membersCount = value.members_count
        val flexOwner = value.owner
        val image = value.image
        val id = value.id
        val owner: User = User(
                firstName = flexOwner?.firstName,
                lastName = flexOwner?.lastName,
                username = flexOwner?.username,
                bio = flexOwner?.bio,
                phone = flexOwner?.phone
        )
        return Flex(
                id = id,
                title = title,
                description = description,
                image = image,
                friendsCount = friendsCount,
                membersCount = membersCount,
                owner = owner
        )
    }

}