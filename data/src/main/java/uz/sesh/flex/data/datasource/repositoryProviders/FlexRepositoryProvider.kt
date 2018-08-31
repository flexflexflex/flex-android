package uz.sesh.flex.data.datasource.repositoryProviders

import android.content.Context
import uz.sesh.flex.data.datasource.repository.FeedFlexRepositoryImpl
import uz.sesh.flex.domain.repository.FlexFeedRepository

class FlexRepositoryProvider(){
    fun provideFlexRepository(context: Context): FlexFeedRepository {
        return FeedFlexRepositoryImpl(context)
    }
}