package uz.sesh.flex.data.datasource.repositoryProviders

import android.content.Context
import uz.sesh.flex.data.datasource.repository.UserRepositoryImpl
import uz.sesh.flex.domain.repository.UserRepository

class UserRepositoryProvider {
    fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryImpl(context)
    }
}