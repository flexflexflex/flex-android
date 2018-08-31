package uz.sesh.flex.domain.repository

import io.reactivex.Single
import uz.sesh.flex.domain.model.User

interface UserRepository {
    fun getUser(): Single<User>
    fun isUsernameIsFree(username: String): Single<Boolean>
}