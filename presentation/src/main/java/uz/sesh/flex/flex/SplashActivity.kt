package uz.sesh.flex.flex

import android.os.Bundle
import android.os.PersistableBundle
import uz.sesh.flex.data.datasource.repositoryProviders.AuthRepositoryProvider
import uz.sesh.flex.domain.repository.AuthRepository
import uz.sesh.flex.flex.main.MainActivity
import uz.sesh.flex.flex.registration.phone.RegistrationActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var authRepository: AuthRepository = AuthRepositoryProvider().provideAuthRepository(this)
        if (authRepository.getUserToken() == "") {
            openActivity(RegistrationActivity::class.java)
        } else {
            openActivity(MainActivity::class.java)
        }
    }
}