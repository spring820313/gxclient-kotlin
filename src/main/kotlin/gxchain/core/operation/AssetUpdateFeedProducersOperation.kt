package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetUpdateFeedProducersOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var asset_to_update: GrapheneId,
    var new_feed_producers: List<GrapheneId>,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetUpdateFeedProducersOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder)
        this.asset_to_update.serialize(encoder)
        encoder.encodeVariableUInt(this.new_feed_producers.size.toLong())
        for(x in this.new_feed_producers) {
            x.serialize(encoder)
        }
        encoder.encodeVariableUInt(0L)
        return true
    }
}