package uz.sesh.flex.data.datasource.repositoryProviders

import android.content.Context
import uz.sesh.flex.data.datasource.repository.AuthRepositoryImpl
import uz.sesh.flex.domain.repository.AuthRepository

class AuthRepositoryProvider{
    fun provideAuthRepository(context: Context):AuthRepository{

        return AuthRepositoryImpl(context)
    }
}