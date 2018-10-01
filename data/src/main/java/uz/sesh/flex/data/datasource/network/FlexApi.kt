package uz.sesh.flex.data.datasource.network

import android.content.Context
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationRequest
import uz.sesh.flex.data.datasource.models.registrationBySms.RegistrationResponse
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationRequest
import uz.sesh.flex.data.datasource.models.smsConfirmation.SmsConfirmationResponse
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*
import uz.sesh.flex.data.datasource.InvalidAuthLiveData
import uz.sesh.flex.data.datasource.PreferenceManager
import uz.sesh.flex.data.datasource.models.feed.FeedResponse
import uz.sesh.flex.data.datasource.models.user.CheckUsernameResponse
import uz.sesh.flex.data.datasource.models.user.UserResponse
import uz.sesh.flex.domain.model.User


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
    @GET("/api/v1/user/")
    fun getUser(): Single<UserResponse>

    //checks username is free
    @GET("/api/v1/auth/check/{username}/")
    fun checkIsValidUsername(@Path("username") username: String=""): Single<CheckUsernameResponse>

    @PATCH("/api/v1/user/")
    fun partialUpdateUser(@Body user: UserResponse): Single<UserResponse>

    companion object Factory {
        private lateinit var api: FlexApi
        private lateinit var retrofit: Retrofit
        private lateinit var preferenceManager: PreferenceManager
        fun create(context: Context): FlexApi {
            if (::api.isInitialized) {
                return api
            }
            if (::retrofit.isInitialized) {
                api = retrofit.create(FlexApi::class.java)
                return api
            }
            val retrofit = provideRetrofit(provideOkHttpClient(provideInterceptor(providePrefManager(context).getToken())))
            return retrofit.create(FlexApi::class.java)
        }

        private fun provideRetrofit(client: OkHttpClient): Retrofit {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    //.client(okHttpClientBuilder.build())
                    .baseUrl("http://18.224.29.19")
                    .build()
            return retrofit
        }

        private fun providePrefManager(context: Context): PreferenceManager {
            if (::preferenceManager.isInitialized)
                return preferenceManager
            preferenceManager = PreferenceManager(context)
            return preferenceManager
        }

        private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
            var builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
            //if (BuildConfig.DEBUG)
            builder.addInterceptor(provideLoginingInterceptor())

            return builder.build()
        }

        private fun provideLoginingInterceptor(): Interceptor {
            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return logging
        }

        private fun provideInterceptor(token: String): Interceptor {
            var interceptor = Interceptor { chain ->
                var originalRequest = chain.request()

                var request = originalRequest.newBuilder()
                        .header("authorization", token)
                        .method(originalRequest.method(), originalRequest.body())
                        .build()

                var response: Response = chain.proceed(request)
                if (response.code() == 403) {
                    InvalidAuthLiveData.getAuthErrorObservable()?.postValue(InvalidAuthLiveData.EVENT.logout)
                    return@Interceptor response
                }
                return@Interceptor response

            }
            return interceptor
        }
    }
}