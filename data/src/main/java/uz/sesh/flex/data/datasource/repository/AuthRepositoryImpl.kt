package uz.sesh.flex.data.datasource.repository

import android.content.Context
import io.reactivex.Single
import uz.sesh.flex.data.datasource.PreferenceManager
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationRequest
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationRequest
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.AuthSmsConfirmation
import uz.sesh.flex.domain.model.AuthByPhoneData
import uz.sesh.flex.domain.repository.AuthRepository

class AuthRepositoryImpl(var context: Context) : AuthRepository {
    private var preferenceManager: PreferenceManager = PreferenceManager(context = context)
    override fun getUserToken(): String {
        return preferenceManager.getToken()
    }

    override fun signByPhoneNumber(phoneNumber: String): Single<AuthByPhoneData> {
        return FlexApi.create(context).authUser(RegistrationRequest(phone = phoneNumber)).map {
            return@map AuthByPhoneData(it.millis)
        }

    }

    override fun confirmPhoneBySms(phoneNumber: String, code: String): Single<AuthSmsConfirmation> {
        return FlexApi.create(context).verifySms(SmsConfirmationRequest(phone = phoneNumber, code = code)).map {
            preferenceManager.saveToken(it.token)
            return@map AuthSmsConfirmation(it.token,it.newUser)
        }
    }
}