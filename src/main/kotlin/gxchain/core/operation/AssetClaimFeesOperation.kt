package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AccountUpdateExtensions
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetClaimFeesOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var amount_to_claim: AssetAmount,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetClaimFeesOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        this.amount_to_claim.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}