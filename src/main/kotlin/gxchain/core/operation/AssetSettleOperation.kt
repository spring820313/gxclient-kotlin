package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetSettleOperation(
    var fee: AssetAmount,
    var account: GrapheneId,
    var amount: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetSettleOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account.serialize(encoder);
        this.amount.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}