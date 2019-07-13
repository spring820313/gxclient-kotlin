package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class PriceFeed(var maintenance_collateral_ratio:Int,
                var maximum_short_squeeze_ratio:Int,
                var settlement_price:Price,
                var core_exchange_rate:Price): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeShort(this.maintenance_collateral_ratio.toShort());
        encoder.encodeShort(this.maximum_short_squeeze_ratio.toShort());
        this.settlement_price.serialize(encoder);
        this.core_exchange_rate.serialize(encoder);
        return true
    }
}