package com.artemissoftware.thetisproto.repository

import androidx.datastore.core.DataStore
import com.artemissoftware.thetisproto.UserStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserRepository(private val protoDataStore: DataStore<UserStore>) {

    suspend fun saveUserColorPreference(alpha: String, color: String) {
        protoDataStore.updateData { store ->
            store.toBuilder()
                .setAlpha(alpha)
                .setColor(color)
                .build()
        }
    }

    suspend fun getUserColorPreference(): Flow<Pair<String, String>> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.alpha to protoBuilder.color
            }
    }
}