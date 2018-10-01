package uz.sesh.flex.data.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class InvalidAuthLiveData {
    enum class EVENT {
        logout,
    }
    companion object {
        private var eventsLiveData: MutableLiveData<EVENT>? = null

        fun getAuthErrorObservable(): MutableLiveData<EVENT>? {
            if (eventsLiveData == null) {
                eventsLiveData = MutableLiveData<EVENT>()
            }
            return eventsLiveData
        }
        fun clearData(){
            eventsLiveData = null
        }

    }

}