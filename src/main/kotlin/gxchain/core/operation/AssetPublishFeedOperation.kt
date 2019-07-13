package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*

class AssetPublishFeedOperation(
    var fee: AssetAmount,
    var publisher: GrapheneId,
    var asset_id: GrapheneId,
    var feed: PriceFeed,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetPublishFeedOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.publisher.serialize(encoder);
        this.asset_id.serialize(encoder);
        this.feed.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}