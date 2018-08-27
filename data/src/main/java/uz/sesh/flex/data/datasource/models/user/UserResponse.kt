package uz.sesh.flex.data.datasource.models.user

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

data class UserResponse(@SerializedName("first_name")
                val firstName: String?,
                        @SerializedName("last_name")
                val lastName: String?,
                        @SerializedName("username")
                val username: String?,
                        @SerializedName("bio")
                val bio: String?,
                        @SerializedName("phone")
                val phone: String?)