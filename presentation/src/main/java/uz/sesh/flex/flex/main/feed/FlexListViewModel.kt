package uz.sesh.flex.flex.main.feed

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.domain.repository.FlexFeedRepository
import uz.sesh.flex.domain.repository.UserRepository
import uz.sesh.flex.flex.main.profile.ProfileFragmentViewModel

class FlexListViewModel(private var flexFeedRepository: FlexFeedRepository) : ViewModel() {
    private var flexListData: MutableLiveData<ArrayList<Flex>> = MutableLiveData()
    public var loadingStatus:ObservableField<Boolean> = ObservableField(false)
    public fun getList(offset: Int): MutableLiveData<ArrayList<Flex>> {
        requestFeed(offset)
        return flexListData
    }

    private fun requestFeed(offset: Int) {
        flexFeedRepository.getFeed(offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    loadingStatus.set(true)
                }
                .doFinally {
                    loadingStatus.set(false)
                }
                .subscribe({
                    flexListData.postValue(it)
                }, {

                })
    }

    /**
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the BusRepository can be passed in a public method.
     */
    internal class Factory(var flexFeedRepository: FlexFeedRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FlexListViewModel(flexFeedRepository) as T
        }
    }
}