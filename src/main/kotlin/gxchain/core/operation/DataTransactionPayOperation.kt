package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class DataTransactionPayOperation(
    var fee: AssetAmount,
    var request_id: String,
    var from: GrapheneId,
    var to: GrapheneId,
    var amount: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.DataTransactionPayOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder);
        this.from.serialize(encoder);
        this.to.serialize(encoder);
        this.amount.serialize(encoder);
        encoder.encodeString(this.request_id);

        encoder.encodeVariableUInt(0)
        return true
    }
}