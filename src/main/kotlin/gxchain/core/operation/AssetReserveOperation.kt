package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import gxchain.http.model.PriceFeed


class AssetReserveOperation(
    var fee: AssetAmount,
    var payer: GrapheneId,
    var amount_to_reserve: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetReserveOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.payer.serialize(encoder);
        this.amount_to_reserve.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}