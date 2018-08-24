package uz.sesh.flex.data.datasource.network

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationRequest
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationResponse
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationRequest
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationResponse

interface FlexApi {
    @POST("") fun authUser(@Body request: RegistrationRequest):Single<RegistrationResponse>
    @POST("") fun verifySms(@Body request: SmsConfirmationRequest):Single<SmsConfirmationResponse>
    companion object Factory {
        fun create(): FlexApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("")
                    .build()
            return retrofit.create(FlexApi::class.java)
        }
    }
}