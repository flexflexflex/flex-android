package uz.sesh.flex.data.datasource.models.flex

import com.google.gson.annotations.SerializedName
import uz.sesh.flex.data.datasource.models.user.UserResponse

data class FlexResponse(
        @SerializedName("title")val title:String,
        @SerializedName("description") val description:String,
        @SerializedName("friends_count")val friends_count: Int,
        @SerializedName("members_count")val members_count: Int,
        @SerializedName("owner")val owner: UserResponse)