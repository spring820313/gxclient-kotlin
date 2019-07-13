package gxchain.http.utils

import com.squareup.moshi.*
import gxchain.http.model.AccountAuthsMap
import gxchain.http.model.GrapheneId
import java.io.IOException


class AccountAuthsMapAdapter : JsonAdapter<AccountAuthsMap>() {

    companion object {
        val FACTORY = Factory { type, _, _ ->
            return@Factory if (type === AccountAuthsMap::class.java) {
                AccountAuthsMapAdapter()
            } else {
                null
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): AccountAuthsMap? {
        var authMap = mutableMapOf<GrapheneId, Short>()
        reader.beginArray()
        while (reader.hasNext()) {
            reader.beginArray()
            val account = reader.nextString()
            val id = reader.nextInt()
            reader.endArray()
            authMap.put(GrapheneId(account), id.toShort())
        }
        reader.endArray()
        return AccountAuthsMap(authMap)
    }

    @Throws(IOException::class)
    override fun toJson(
        writer: JsonWriter,
        value: AccountAuthsMap?
    ) {
        writer.beginArray()
        value?.auth?.forEach {
            writer.value(it.key.toString())
            writer.value(it.value)
        }
        writer.endArray()
    }

    override fun toString(): String {
        return "JsonAdapter(AccountAuthsMap)"
    }
}