package uz.sesh.flex.data.datasource.mapper

import uz.sesh.flex.data.datasource.models.user.UserResponse
import uz.sesh.flex.domain.model.User

class UserMapper() : Mapper<UserResponse?, User?>() {
    companion object {
        var instance = UserMapper()
    }

    override fun map(value: UserResponse?): User? {
        return User(
                value?.firstName,
                value?.lastName,
                value?.username,
                value?.bio,
                value?.phone
        )
    }

    override fun reverseMap(value: User?): UserResponse? {
        return UserResponse(
                value?.firstName,
                value?.lastName,
                value?.username,
                value?.bio,
                value?.phone
        )
    }

}