package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class BalanceClaimOperation(
    var fee: AssetAmount,
    var deposit_to_account: GrapheneId,
    var balance_to_claim: GrapheneId,
    var balance_owner_key: String,
    var total_claimed: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.BalanceClaimOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.deposit_to_account.serialize(encoder);
        this.balance_to_claim.serialize(encoder);
        encoder.encodePubKey(this.balance_owner_key);
        this.total_claimed.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}