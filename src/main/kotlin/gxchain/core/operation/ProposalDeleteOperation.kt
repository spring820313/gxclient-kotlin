package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class ProposalDeleteOperation(
    var fee: AssetAmount,
    var fee_paying_account: GrapheneId,
    var using_owner_authority: Boolean,
    var proposal: GrapheneId,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.ProposalDeleteOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.fee_paying_account.serialize(encoder);
        encoder.encodeBool(this.using_owner_authority)
        this.proposal.serialize(encoder);
        encoder.encodeVariableUInt(0)
        return true
    }
}