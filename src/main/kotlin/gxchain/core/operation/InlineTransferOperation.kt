package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class InlineTransferOperation(
    var fee: AssetAmount,
    var from: GrapheneId,
    var to: GrapheneId,
    var amount: AssetAmount,
    var memo: String,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.InlineTransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder);
        this.from.serialize(encoder);
        this.to.serialize(encoder);
        this.amount.serialize(encoder);
        encoder.encodeString(this.memo);
        encoder.encodeVariableUInt(0);
        return true
    }
}