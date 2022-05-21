package com.artemissoftware.thetisproto.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.artemissoftware.thetisproto.UserStore
import com.artemissoftware.thetisproto.serializer.UserStoreSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val DATA_STORE_FILE_NAME = "user_store.pb"
val Context.userDataStore: DataStore<UserStore> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

class UserRepository(context: Context) {

    private val protoDataStore: DataStore<UserStore> = context.userDataStore

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
}