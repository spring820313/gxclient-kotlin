package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import gxchain.http.model.SpecialAuth
import gxchain.http.model.SpecialAuthorityType

class SpecialAuthAdapter {
    @FromJson
    internal fun fromJson(s: String): SpecialAuth? {
        val moshi = Moshi.Builder().add(GrapheneIdAdapter()).build()
        val lst = moshi.adapter(List::class.java).fromJson(s)
        if(lst?.size != 2) {
            return null
        }
        val opType = lst[0] as Byte
        return SpecialAuth(SpecialAuthorityType.fromByte(opType), lst[1]!!)
    }

    @ToJson
    internal fun toJson(auth: SpecialAuth): String {
        val moshi = Moshi.Builder().add(GrapheneIdAdapter()).build()
        val lst = listOf<Any>(auth.type.op, auth.auth)
        val s = moshi.adapter(List::class.java).toJson(lst)
        return s
    }
}