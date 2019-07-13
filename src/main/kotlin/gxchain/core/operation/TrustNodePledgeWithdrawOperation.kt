package gxchain.core.operation


import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class TrustNodePledgeWithdrawOperation(
    var fee: AssetAmount,
    var witness_account: GrapheneId):Operation() {
    override fun opTye(): Byte {
        return OpType.TrustNodePledgeWithdrawOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.witness_account.serialize(encoder);

        return true
    }
}