package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import java.util.*


class LimitOrderCreateOperation(
    var fee: AssetAmount,
    var seller: GrapheneId,
    var amount_to_sell: AssetAmount,
    var min_to_receive: AssetAmount,
    var expiration: Date,
    var fill_or_kill: Boolean,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.LimitOrderCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.seller.serialize(encoder)
        this.amount_to_sell.serialize(encoder)
        this.min_to_receive.serialize(encoder)
        encoder.encodeTimestampMs(this.expiration.time)
        encoder.encodeBool(this.fill_or_kill)
        encoder.encodeVariableUInt(0)
        return true
    }
}