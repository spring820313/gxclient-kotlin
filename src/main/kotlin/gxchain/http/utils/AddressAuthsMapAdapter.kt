package gxchain.http.utils

import com.squareup.moshi.*
import gxchain.http.model.Address
import gxchain.http.model.AddressAuthsMap
import java.io.IOException


class AddressAuthsMapAdapter : JsonAdapter<AddressAuthsMap>() {

    companion object {
        val FACTORY = Factory { type, _, _ ->
            return@Factory if (type === AddressAuthsMap::class.java) {
                AddressAuthsMapAdapter()
            } else {
                null
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): AddressAuthsMap? {
        var authMap = mutableMapOf<Address, Short>()
        reader.beginArray()
        while (reader.hasNext()) {
            reader.beginArray()
            val account = reader.nextString()
            val id = reader.nextInt()
            reader.endArray()
            authMap.put(Address(account), id.toShort())
        }
        reader.endArray()
        return AddressAuthsMap(authMap)
    }

    @Throws(IOException::class)
    override fun toJson(
        writer: JsonWriter,
        value: AddressAuthsMap?
    ) {
        writer.beginArray()
        value?.auth?.forEach {
            writer.value(it.key.toString())
            writer.value(it.value)
        }
        writer.endArray()
    }

    override fun toString(): String {
        return "JsonAdapter(AddressAuthsMap)"
    }
}