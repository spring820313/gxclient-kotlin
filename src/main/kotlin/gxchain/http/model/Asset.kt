package gxchain.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Asset (
    var id:GrapheneId,
    var symbol:String,
    var precision:Int,
    var issuer:String,
    var dynamic_asset_data_id:String
)