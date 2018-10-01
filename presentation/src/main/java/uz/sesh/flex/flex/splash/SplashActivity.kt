package uz.sesh.flex.flex.splash

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import uz.sesh.flex.data.datasource.InvalidAuthLiveData
import uz.sesh.flex.data.datasource.PreferenceManager
import uz.sesh.flex.data.datasource.repositoryProviders.AuthRepositoryProvider
import uz.sesh.flex.data.datasource.repositoryProviders.UserRepositoryProvider
import uz.sesh.flex.domain.repository.AuthRepository
import uz.sesh.flex.domain.repository.UserRepository
import uz.sesh.flex.flex.BaseActivity
import uz.sesh.flex.flex.R
import uz.sesh.flex.flex.main.MainActivity
import uz.sesh.flex.flex.registration.ProfileFillActivity
import uz.sesh.flex.flex.registration.phone.RegistrationActivity

class SplashActivity : BaseActivity(), SplashContract.View {
    override fun openMainActivity() {
        openActivityClear(MainActivity::class.java)
        finish()
    }

    override fun openProfileFill() {
        openActivityClear(ProfileFillActivity::class.java)
        finish()
    }

    override fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showNoInternetConnection(show: Boolean) {
        //Toast.makeText(this,"BLYAT",Toast.LENGTH_LONG).show()
    }

    var presenter: SplashContract.Presenter? = null
    var authRepository: AuthRepository? = null
    var userRepository: UserRepository? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        authRepository = AuthRepositoryProvider().provideAuthRepository(this)
        userRepository = UserRepositoryProvider().provideUserRepository(this)
        presenter = SplashPresenter(userRepository!!,this)
        InvalidAuthLiveData.getAuthErrorObservable()?.observe(this, Observer {
            it?.let {
                if (it == InvalidAuthLiveData.EVENT.logout) {
                    PreferenceManager(this).clearToken()
                    InvalidAuthLiveData.clearData()
                    checkLocalUser()
                }
            }
        })
        checkLocalUser()

    }
    fun checkLocalUser(){
        if (authRepository?.getUserToken() == "") {
            openActivityClear(RegistrationActivity::class.java)
        } else {
            (presenter as SplashPresenter).checkUserProfileFill()
        }
    }

}