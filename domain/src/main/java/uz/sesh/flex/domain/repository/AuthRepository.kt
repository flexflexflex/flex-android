package uz.sesh.flex.domain.repository

import io.reactivex.Single
import uz.sesh.flex.domain.model.AuthSmsConfirmation
import uz.sesh.flex.domain.model.AuthByPhoneData

interface AuthRepository {
    fun signByPhoneNumber(phoneNumber: String): Single<AuthByPhoneData>
    fun confirmPhoneBySms(phoneNumber: String,code: String): Single<AuthSmsConfirmation>
    fun getUserToken():String
}