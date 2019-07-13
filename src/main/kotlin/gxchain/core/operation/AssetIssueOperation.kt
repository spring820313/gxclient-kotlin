package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*


class AssetIssueOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var asset_to_issue: GrapheneId,
    var issue_to_account: Price,
    var memo:Memo? = null,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetIssueOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        this.issuer.serialize(encoder);
        this.asset_to_issue.serialize(encoder);
        this.issue_to_account.serialize(encoder);
        encoder.encodeBool(this.memo != null);
        if(this.memo != null) {
            this.memo!!.serialize(encoder);
        }
        encoder.encodeVariableUInt(0L)
        return true
    }
}