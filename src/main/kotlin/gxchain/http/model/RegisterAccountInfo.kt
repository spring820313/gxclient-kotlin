package gxchain.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterAccountInfo (
    var name:String,
    var active_key:String,
    var owner_key:String?,
    var memo_key:String?
)

@JsonClass(generateAdapter = true)
data class RegisterAccount (
    var account:RegisterAccountInfo
)