package uz.sesh.flex.data.datasource.mapper

import uz.sesh.flex.data.datasource.Mapper
import uz.sesh.flex.data.datasource.models.user.UserResponse
import uz.sesh.flex.domain.model.User

class UserMapper() : Mapper {
    companion object {
        fun map(userResponse: UserResponse): User {
            return User(
                    userResponse.firstName,
                    userResponse.lastName,
                    userResponse.username,
                    userResponse.bio,
                    userResponse.phone
            )
        }
        fun map(user: User): UserResponse {
            return UserResponse(
                    user.firstName,
                    user.lastName,
                    user.username,
                    user.bio,
                    user.phone
            )
        }
    }

}