package uz.sesh.flex.data.datasource.models.smsConfirmation

import com.google.gson.annotations.SerializedName

data class SmsConfirmationRequest(@SerializedName("phone") val phone: String,
                                  @SerializedName("code") val code: String
)