package uz.sesh.flex.data.datasource.repository

import android.content.Context
import io.reactivex.Single
import uz.sesh.flex.data.datasource.mapper.FlexMapper
import uz.sesh.flex.data.datasource.network.FlexApi
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.domain.model.User
import uz.sesh.flex.domain.repository.FlexFeedRepository

class FeedFlexRepositoryImpl(var context: Context) : FlexFeedRepository {
    override fun getFeed(page: Int): Single<ArrayList<Flex>> {
        return FlexApi.create(context).getFeed(page).map {
            return@map FlexMapper.instance.map(it.flexes)
        }
    }
}