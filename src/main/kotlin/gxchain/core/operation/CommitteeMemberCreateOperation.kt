package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class CommitteeMemberCreateOperation(
    var fee: AssetAmount,
    var committee_member_account: GrapheneId,
    var url: String):Operation() {
    override fun opTye(): Byte {
        return OpType.CommitteeMemberCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.committee_member_account.serialize(encoder);
        encoder.encodeString(this.url);
        return true
    }
}