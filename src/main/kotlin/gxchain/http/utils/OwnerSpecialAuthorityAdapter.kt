package gxchain.http.utils

import com.squareup.moshi.*
import gxchain.http.model.*
import java.io.IOException

class OwnerSpecialAuthorityAdapter : JsonAdapter<OwnerSpecialAuthority>() {
    private val moshi = Moshi.Builder().build()
    companion object {
        val FACTORY = Factory { type, _, _ ->
            return@Factory if (type === OwnerSpecialAuthority::class.java) {
                OwnerSpecialAuthorityAdapter()
            } else {
                null
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): OwnerSpecialAuthority? {
        var lst = mutableListOf<Any>()
        reader.beginArray()

        var t = SpecialAuthorityType.SpecialAuthorityTypeNoSpecial
        if(reader.hasNext()) {
            val type = reader.nextInt().toByte()
            lst.add(type)
            t = SpecialAuthorityType.fromByte(type)
        }
        if(reader.hasNext()) {
            if(t == SpecialAuthorityType.SpecialAuthorityTypeNoSpecial) {
                val o = moshi.adapter(NoSpecialAuthority::class.java).fromJson(reader)
                lst.add(o as Any)
            } else {
                val o = moshi.adapter(TopHoldersSpecialAuthority::class.java).fromJson(reader)
                lst.add(o as Any)
            }

        }
        reader.endArray()
        return OwnerSpecialAuthority(SpecialAuth(t, lst[1]))
    }

    @Throws(IOException::class)
    override fun toJson(
        writer: JsonWriter,
        value: OwnerSpecialAuthority?
    ) {
        writer.beginArray()
        writer.value(value?.specialAuth?.type?.op)

        if(value?.specialAuth?.type == SpecialAuthorityType.SpecialAuthorityTypeNoSpecial) {
            moshi.adapter(NoSpecialAuthority::class.java).toJson(writer, value.specialAuth.auth as NoSpecialAuthority)
        } else {
            moshi.adapter(TopHoldersSpecialAuthority::class.java).toJson(writer, value?.specialAuth?.auth as TopHoldersSpecialAuthority)
        }
        writer.endArray()
    }

    override fun toString(): String {
        return "JsonAdapter(OwnerSpecialAuthorityAdapter)"
    }
}