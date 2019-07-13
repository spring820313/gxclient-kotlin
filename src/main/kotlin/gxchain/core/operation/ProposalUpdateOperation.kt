package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class ProposalUpdateOperation(
    var fee: AssetAmount,
    var fee_paying_account: GrapheneId,
    var proposal: GrapheneId,
    var active_approvals_to_add: List<GrapheneId>,
    var active_approvals_to_remove: List<GrapheneId>,
    var owner_approvals_to_add: List<GrapheneId>,
    var owner_approvals_to_remove: List<GrapheneId>,
    var key_approvals_to_add: List<GrapheneId>,
    var key_approvals_to_remove: List<GrapheneId>,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.ProposalUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.fee_paying_account.serialize(encoder);
        this.proposal.serialize(encoder);

        encoder.encodeVariableUInt(this.active_approvals_to_add.size.toLong())
        for(o in this.active_approvals_to_add) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.active_approvals_to_remove.size.toLong())
        for(o in this.active_approvals_to_remove) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.owner_approvals_to_add.size.toLong())
        for(o in this.owner_approvals_to_add) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.owner_approvals_to_remove.size.toLong())
        for(o in this.owner_approvals_to_remove) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.key_approvals_to_add.size.toLong())
        for(o in this.key_approvals_to_add) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(this.key_approvals_to_remove.size.toLong())
        for(o in this.key_approvals_to_remove) {
            o.serialize(encoder)
        }

        encoder.encodeVariableUInt(0)
        return true
    }
}