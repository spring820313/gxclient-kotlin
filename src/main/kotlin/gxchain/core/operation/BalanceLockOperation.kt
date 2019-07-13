package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.Memo
import gxchain.http.model.OpType
import java.util.*


class BalanceLockOperation(
    var fee: AssetAmount,
    var account: GrapheneId,
    var create_date_time: Date,
    var program_id: String,
    var amount: AssetAmount,
    var lock_days: Long,
    var interest_rate: Long,
    var memo: Memo,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.BalanceLockOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account.serialize(encoder)
        encoder.encodeTimestampMs(this.create_date_time.time)
        encoder.encodeString(this.program_id)
        this.amount.serialize(encoder)
        encoder.encodeInt(this.lock_days.toInt())
        encoder.encodeInt(this.interest_rate.toInt())
        this.memo.serialize(encoder)
        encoder.encodeVariableUInt(0L)
        return true
    }
}