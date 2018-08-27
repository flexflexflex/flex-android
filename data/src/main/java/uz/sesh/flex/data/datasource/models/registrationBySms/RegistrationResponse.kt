package uz.sesh.flex.data.datasource.models.registrationBySms

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
        @SerializedName("result") val result: String,
        @SerializedName("millis")val millis: Int
)
