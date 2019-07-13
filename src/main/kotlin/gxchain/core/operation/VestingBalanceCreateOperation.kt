package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import gxchain.http.model.VestingPolicy


class VestingBalanceCreateOperation(
    var fee: AssetAmount,
    var creator: GrapheneId,
    var owner: GrapheneId,
    var amount: AssetAmount,
    var policy: VestingPolicy):Operation() {
    override fun opTye(): Byte {
        return OpType.VestingBalanceCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.creator.serialize(encoder);
        this.owner.serialize(encoder);
        this.amount.serialize(encoder);
        this.policy.serialize(encoder);
        return true
    }
}