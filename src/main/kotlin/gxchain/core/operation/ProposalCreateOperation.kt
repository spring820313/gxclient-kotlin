package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*
import java.util.*


class ProposalCreateOperation(
    var fee: AssetAmount,
    var fee_paying_account: GrapheneId,
    var expiration_time: Date,
    var proposed_ops: List<OperationEnvelopeHolder>,
    var review_period_seconds: Long? = null,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.ProposalCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.fee_paying_account.serialize(encoder)
        encoder.encodeTimestampMs(this.expiration_time.time)
        encoder.encodeVariableUInt(this.proposed_ops.size.toLong())
        for(p in this.proposed_ops) {
            p.serialize(encoder)
        }
        encoder.encodeBool(this.review_period_seconds != null)
        if(this.review_period_seconds != null)
            encoder.encodeInt(this.review_period_seconds!!.toInt())
        encoder.encodeVariableUInt(0)
        return true
    }
}