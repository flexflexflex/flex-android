package uz.sesh.flex.data.datasource.repository

import io.reactivex.Single
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.UserRepository

class UserRepositoryImpl() : UserRepository {
    override fun getUser(): Single<User> {
        FlexApi.create().getUser().map {

        }
        return Single.just(User("","","","",""))
    }

}