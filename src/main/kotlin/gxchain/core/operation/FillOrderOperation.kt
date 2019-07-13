package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class FillOrderOperation(
    var fee: AssetAmount,
    var order_id: GrapheneId,
    var account_id: GrapheneId,
    var pays: AssetAmount,
    var receives: AssetAmount):Operation() {
    override fun opTye(): Byte {
        return OpType.FillOrderOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder);
        this.order_id.serialize(encoder);
        this.account_id.serialize(encoder);
        this.pays.serialize(encoder);
        this.receives.serialize(encoder);
        return true
    }
}