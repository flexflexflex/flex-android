package uz.sesh.flex.domain.repository

import io.reactivex.Single
import uz.sesh.flex.domain.model.AuthSmsConfirmation
import uz.sesh.flex.domain.model.AuthSmsResponse

interface AuthRepository {
    fun signByPhoneNumber(phoneNumber: String): Single<AuthSmsResponse>
    fun confirmPhoneBySms(code: String): Single<AuthSmsConfirmation>
}