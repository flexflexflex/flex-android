package uz.sesh.flex.domain.usecase

import io.reactivex.Single
import uz.sesh.flex.domain.model.AuthSmsConfirmation
import uz.sesh.flex.domain.model.AuthSmsResponse
import uz.sesh.flex.domain.repository.AuthRepository

class AuthUseCase(var repository: AuthRepository) {
    fun signUserWithSms(number: String): Single<AuthSmsResponse> {
        return repository.signByPhoneNumber(number)
    }

    fun confirmSms(code: String): Single<AuthSmsConfirmation> {
        return repository.confirmPhoneBySms(code)
    }
}