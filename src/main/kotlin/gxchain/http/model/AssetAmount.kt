package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class AssetAmount(var amount:Long, var asset_id:GrapheneId):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeLong(this.amount)
        this.asset_id.serialize(encoder)
        return true
    }
}