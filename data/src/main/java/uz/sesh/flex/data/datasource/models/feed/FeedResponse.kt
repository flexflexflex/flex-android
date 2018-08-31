package uz.sesh.flex.data.datasource.models.feed

import com.google.gson.annotations.SerializedName
import uz.sesh.flex.data.datasource.models.flex.FlexResponse

data class FeedResponse(@SerializedName("count") val count: Int,
                        @SerializedName("results") val flexes: ArrayList<FlexResponse>)