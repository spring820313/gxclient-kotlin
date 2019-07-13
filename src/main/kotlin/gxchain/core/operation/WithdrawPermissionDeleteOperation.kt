package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class WithdrawPermissionDeleteOperation(
    var fee: AssetAmount,
    var withdraw_from_account: GrapheneId,
    var authorized_account: GrapheneId,
    var withdraw_permission: GrapheneId):Operation() {
    override fun opTye(): Byte {
        return OpType.WithdrawPermissionDeleteOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.withdraw_from_account.serialize(encoder)
        this.authorized_account.serialize(encoder)
        this.withdraw_permission.serialize(encoder)
        return true
    }
}