package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class BitassetOptions(var feed_lifetime_sec:Long,
                   var minimum_feeds:Int,
                   var force_settlement_delay_sec:Long,
                   var force_settlement_offset_percent:Int,
                   var maximum_force_settlement_volume:Int,
                   var short_backing_asset:GrapheneId,
                   var extensions:List<Any> = listOf()): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeInt(this.feed_lifetime_sec.toInt());
        encoder.encodeByte(this.minimum_feeds.toByte());
        encoder.encodeInt(this.force_settlement_delay_sec.toInt());
        encoder.encodeShort(this.force_settlement_offset_percent.toShort());
        encoder.encodeShort(this.maximum_force_settlement_volume.toShort());
        this.short_backing_asset.serialize(encoder);
        encoder.encodeVariableUInt(0);
        return true
    }
}