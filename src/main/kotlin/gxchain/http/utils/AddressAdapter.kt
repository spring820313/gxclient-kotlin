package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import gxchain.http.model.Address


class AddressAdapter {

    @FromJson
    internal fun fromJson(address: String): Address {
        return Address(address)
    }

    @ToJson
    internal fun toJson(address: Address): String {
        return address.toString()
    }
}