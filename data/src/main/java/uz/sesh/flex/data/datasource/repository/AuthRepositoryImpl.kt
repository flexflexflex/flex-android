package uz.sesh.flex.data.datasource.repository

import io.reactivex.Single
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationRequest
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.AuthSmsConfirmation
import uz.sesh.flex.domain.model.AuthSmsResponse
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.AuthRepository

class AuthRepositoryImpl:AuthRepository{
    override fun signByPhoneNumber(phoneNumber: String): Single<AuthSmsResponse> {
        FlexApi.create().authUser(RegistrationRequest())
        return Single.just(AuthSmsResponse(123))
    }

    override fun confirmPhoneBySms(code: String): Single<AuthSmsConfirmation> {
        return Single.just(AuthSmsConfirmation(User("Arslan","xyarim","998935823825","lkajhsdkljaskhkjlk")))
    }
}