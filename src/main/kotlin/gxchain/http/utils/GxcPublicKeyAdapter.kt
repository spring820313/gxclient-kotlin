package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import gxchain.core.crypto.GxcPublicKey
import gxchain.http.model.Address

class GxcPublicKeyAdapter {
    @FromJson
    internal fun fromJson(pub: String): GxcPublicKey {
        return GxcPublicKey(pub)
    }

    @ToJson
    internal fun toJson(pub: GxcPublicKey): String {
        return pub.toString()
    }
}