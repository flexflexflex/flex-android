package uz.sesh.flex.flex.splash

import android.support.design.widget.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile_fill.*
import uz.sesh.flex.data.datasource.repositoryProviders.UserRepositoryProvider
import uz.sesh.flex.domain.repository.UserRepository

class SplashPresenter(var userRepository: UserRepository, var view: SplashContract.View) : SplashContract.Presenter {
    override fun checkUserProfileFill() {
        userRepository.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    view.showLoading(true)
                    view.showNoInternetConnection(false)
                }
                .doFinally {
                    view.showLoading(false)
                }
                .subscribe({
                    if (it.username == null){
                        view.openProfileFill()
                    }else{
                        view.openMainActivity()
                    }
                }, {
                   view.showNoInternetConnection(true)
                })
    }

}