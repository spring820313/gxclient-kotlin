package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class LimitOrderCancelOperation(
    var fee: AssetAmount,
    var fee_paying_account: GrapheneId,
    var order: GrapheneId,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.LimitOrderCancelOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder);
        this.fee_paying_account.serialize(encoder);
        this.order.serialize(encoder);
        encoder.encodeVariableUInt(0);
        return true
    }
}