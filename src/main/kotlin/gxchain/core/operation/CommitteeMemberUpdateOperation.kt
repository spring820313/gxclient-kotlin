package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class CommitteeMemberUpdateOperation(
    var fee: AssetAmount,
    var committee_member: GrapheneId,
    var committee_member_account: GrapheneId,
    var new_url: String? = null):Operation() {
    override fun opTye(): Byte {
        return OpType.CommitteeMemberUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.committee_member.serialize(encoder);
        this.committee_member_account.serialize(encoder);
        encoder.encodeBool(this.new_url != null);
        if(this.new_url != null)
            encoder.encodeString(this.new_url!!);
        return true
    }
}