package uz.sesh.flex.data.datasource.repository

import android.content.Context
import io.reactivex.Single
import uz.sesh.flex.data.datasource.mapper.FlexMapper
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.FlexFeedRepository

class FeedFlexRepositoryImpl(context: Context) : FlexFeedRepository {
    override fun getFeed(page: Int): Single<ArrayList<Flex>> {
        return FlexApi.create().getFeed(page).map {
            return@map FlexMapper.map(it.flexes)
        }
    }
}