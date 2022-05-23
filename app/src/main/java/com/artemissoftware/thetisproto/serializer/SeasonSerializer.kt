package com.artemissoftware.thetisproto.serializer

import androidx.datastore.core.Serializer
import com.artemissoftware.thetisproto.models.SeasonSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object SeasonSerializer : Serializer<SeasonSettings> {

    override val defaultValue: SeasonSettings
        get() = SeasonSettings()

    override suspend fun readFrom(input: InputStream): SeasonSettings {
        return try {
            Json.decodeFromString(
                deserializer = SeasonSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: SeasonSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = SeasonSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}