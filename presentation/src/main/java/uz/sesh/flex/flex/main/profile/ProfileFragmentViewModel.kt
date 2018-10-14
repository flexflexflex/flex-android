package uz.sesh.flex.flex.main.profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import uz.sesh.flex.data.datasource.repositoryProviders.UserRepositoryProvider
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.UserRepository
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_flex_feed_item_list.*
import uz.sesh.flex.flex.main.feed.MyFlexFeedItemRecyclerViewAdapter


class ProfileFragmentViewModel(private var userRepository: UserRepository) : ViewModel() {

    private var userData: MutableLiveData<User> = MutableLiveData()

    fun getUser(): MutableLiveData<User> {
        requestUser()
        return userData
    }

    private fun requestUser() {
        userRepository.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    userData.postValue(it)
                }, {

                })
    }

    /**
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the BusRepository can be passed in a public method.
     */
    internal class Factory(var busRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileFragmentViewModel(busRepository) as T
        }
    }
}