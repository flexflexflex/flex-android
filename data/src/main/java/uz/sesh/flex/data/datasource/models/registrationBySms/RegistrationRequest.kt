package uz.sesh.flex.data.datasource.models.registrationBySms

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
        @SerializedName("phone") val phone: String
)