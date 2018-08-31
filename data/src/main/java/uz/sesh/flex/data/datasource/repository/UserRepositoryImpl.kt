package uz.sesh.flex.data.datasource.repository

import android.content.Context
import io.reactivex.Single
import uz.sesh.flex.data.datasource.mapper.UserMapper
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.UserRepository

class UserRepositoryImpl(var context: Context):UserRepository{
    override fun getUser(): Single<User> {
        return FlexApi.create(context).getUser().map {
            return@map UserMapper.map(it)
        }
    }

    override fun isUsernameIsFree(username: String): Single<Boolean> {
        return FlexApi.create(context).checkIsValidUsername(username)
    }

}