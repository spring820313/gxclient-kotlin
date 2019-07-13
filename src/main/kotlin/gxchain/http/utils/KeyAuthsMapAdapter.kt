package gxchain.http.utils

import com.squareup.moshi.*
import gxchain.core.crypto.GxcPublicKey
import gxchain.http.model.KeyAuthsMap
import java.io.IOException



class KeyAuthsMapAdapter : JsonAdapter<KeyAuthsMap>() {

    companion object {
        val FACTORY = Factory { type, _, _ ->
            return@Factory if (type === KeyAuthsMap::class.java) {
                KeyAuthsMapAdapter()
            } else {
                null
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): KeyAuthsMap? {
        var authMap = mutableMapOf<GxcPublicKey, Short>()
        reader.beginArray()
        while (reader.hasNext()) {
            reader.beginArray()
            val account = reader.nextString()
            val id = reader.nextInt()
            reader.endArray()
            authMap.put(GxcPublicKey(account), id.toShort())
        }
        reader.endArray()
        return KeyAuthsMap(authMap)
    }

    @Throws(IOException::class)
    override fun toJson(
        writer: JsonWriter,
        value: KeyAuthsMap?
    ) {
        writer.beginArray()
        value?.auth?.forEach {
            writer.value(it.key.toString())
            writer.value(it.value)
        }
        writer.endArray()
    }

    override fun toString(): String {
        return "JsonAdapter(KeyAuthsMap)"
    }
}