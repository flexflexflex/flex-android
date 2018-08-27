package uz.sesh.flex.data.datasource.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationRequest
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationResponse
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationRequest
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationResponse
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query
import uz.sesh.flex.data.datasource.models.feed.FeedResponse
import uz.sesh.flex.data.datasource.models.user.UserResponse


interface FlexApi {
    //Auth
    @POST("/api/v1/auth/code/")
    fun authUser(@Body request: RegistrationRequest): Single<RegistrationResponse>

    @POST("/api/v1/auth/token/")
    fun verifySms(@Body request: SmsConfirmationRequest): Single<SmsConfirmationResponse>

    //Feed
    @GET("/api/v1/flex/")
    fun getFeed(@Query("page") page: Int): Single<FeedResponse>

    //UserResponse
    @GET("/api/v1/auth/user/")
    fun getUser():Single<UserResponse>
    companion object Factory {
        fun create(): FlexApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl("http://192.168.43.45:8000")
                    .build()
            return retrofit.create(FlexApi::class.java)
        }
    }
}