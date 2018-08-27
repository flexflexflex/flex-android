package uz.sesh.flex.domain.repository

import io.reactivex.Single
import uz.sesh.flex.domain.model.Flex

interface FlexFeedRepository {
    fun getFeed(page: Int): Single<ArrayList<Flex>>
}