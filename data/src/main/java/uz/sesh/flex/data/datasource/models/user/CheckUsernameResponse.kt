package uz.sesh.flex.data.datasource.models.user

import com.google.gson.annotations.SerializedName

data class CheckUsernameResponse(@SerializedName("valid") val isValid: Boolean)