package uz.sesh.flex.data.datasource.repositoryProviders

import uz.sesh.flex.data.datasource.repository.AuthRepositoryImpl
import uz.sesh.flex.domain.repository.AuthRepository

class AuthRepositoryProvider{
    fun provideAuthRepository():AuthRepository{
        return AuthRepositoryImpl()
    }
}