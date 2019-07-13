package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class AssetOptions(var max_supply:Long,
                   var max_market_fee:Long,
                   var market_fee_percent:Int,
                   var flags:Int,
                   var description:String,
                   var core_exchange_rate:Price,
                   var issuer_permissions:Int,
                   var blacklist_authorities:List<GrapheneId>,
                   var whitelist_authorities:List<GrapheneId>,
                   var blacklist_markets:List<GrapheneId>,
                   var whitelist_markets:List<GrapheneId>,
                   var extensions:List<Any> = listOf()): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeLong(this.max_supply);
        encoder.encodeLong(this.max_market_fee);
        encoder.encodeShort(this.market_fee_percent.toShort());
        encoder.encodeShort(this.flags.toShort());
        encoder.encodeString(this.description);
        this.core_exchange_rate.serialize(encoder);
        encoder.encodeShort(this.issuer_permissions.toShort());

        encoder.encodeVariableUInt(this.blacklist_authorities.size.toLong())
        for(x in this.blacklist_authorities) {
            x.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.whitelist_authorities.size.toLong())
        for(x in this.whitelist_authorities) {
            x.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.blacklist_markets.size.toLong())
        for(x in this.blacklist_markets) {
            x.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.whitelist_markets.size.toLong())
        for(x in this.whitelist_markets) {
            x.serialize(encoder)
        }

        encoder.encodeVariableUInt(0);
        return true
    }
}