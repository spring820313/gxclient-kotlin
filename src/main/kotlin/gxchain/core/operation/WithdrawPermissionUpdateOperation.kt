package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import java.util.*


class WithdrawPermissionUpdateOperation(
    var fee: AssetAmount,
    var withdraw_from_account: GrapheneId,
    var authorized_account: GrapheneId,
    var permission_to_update: GrapheneId,
    var withdrawal_limit: AssetAmount,
    var withdrawal_period_sec: Int,
    var periods_until_expiration: Int,
    var period_start_time: Date):Operation() {
    override fun opTye(): Byte {
        return OpType.WithdrawPermissionUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.withdraw_from_account.serialize(encoder);
        this.authorized_account.serialize(encoder);
        this.permission_to_update.serialize(encoder);
        this.withdrawal_limit.serialize(encoder);
        encoder.encodeInt(this.withdrawal_period_sec);
        encoder.encodeTimestampMs(this.period_start_time.time)
        encoder.encodeInt(this.periods_until_expiration)
        return true
    }
}