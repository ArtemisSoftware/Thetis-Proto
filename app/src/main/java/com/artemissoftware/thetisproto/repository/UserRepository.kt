package com.artemissoftware.thetisproto.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.artemissoftware.thetisproto.UserStore
import com.artemissoftware.thetisproto.models.Location
import com.artemissoftware.thetisproto.models.SeasonSettings
import com.artemissoftware.thetisproto.models.Seasons
import com.artemissoftware.thetisproto.serializer.SeasonSerializer
import com.artemissoftware.thetisproto.serializer.UserStoreSerializer
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val DATA_STORE_FILE_NAME = "user_store.pb"
val Context.userDataStore: DataStore<UserStore> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

val Context.seasonStore by dataStore(
    fileName = "app-settings.json",
    serializer = SeasonSerializer
)

class UserRepository(context: Context) {

    private val protoDataStore: DataStore<UserStore> = context.userDataStore
    private val seasonStore: DataStore<SeasonSettings> = context.seasonStore

    suspend fun saveUserColorPreference(alpha: Float, color: String) {
        protoDataStore.updateData { store ->
            store.toBuilder()
                .setAlpha(alpha)
                .setColor(color)
                .build()
        }
    }

    suspend fun getUserColorPreference(): Flow<Pair<String, Float>> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.color to protoBuilder.alpha
            }
    }



    suspend fun saveSeason(season: Seasons) {
        seasonStore.updateData {
            it.copy(
                favorite = season,
                knownLocations = mutableListOf(Location(lat = 12345678.600, lng = 25664125.641))//persistentListOf(Location(lat = 12345678.600, lng = 25664125.641))
//                    .mutate { locationList ->
//                        locationList.add(Location(lat = 12345678.600, lng = 25664125.641))
//                    }
            )
        }
    }

    suspend fun getSeason(): Flow<SeasonSettings> {
        return seasonStore.data
    }
}