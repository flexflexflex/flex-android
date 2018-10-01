package uz.sesh.flex.data.datasource.models.smsConfirmation

import com.google.gson.annotations.SerializedName

data class SmsConfirmationResponse(
        @SerializedName("token") val token: String,
        @SerializedName("new_user") val newUser: Boolean
)