package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class Price(var base:AssetAmount,
            var quote:AssetAmount): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        this.base.serialize(encoder)
        this.quote.serialize(encoder)
        return true
    }
}