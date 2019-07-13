package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.Memo
import gxchain.http.model.OpType



class WithdrawPermissionClaimOperation(
    var fee: AssetAmount,
    var withdraw_permission: GrapheneId,
    var withdraw_from_account: GrapheneId,
    var withdraw_to_account: GrapheneId,
    var amount_to_withdraw: AssetAmount,
    var memo: Memo? = null
):Operation() {
    override fun opTye(): Byte {
        return OpType.WithdrawPermissionClaimOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.withdraw_permission.serialize(encoder);
        this.withdraw_from_account.serialize(encoder);
        this.withdraw_to_account.serialize(encoder);
        this.amount_to_withdraw.serialize(encoder);

        if(this.memo != null) {
            encoder.encodeBool(true);
            this.memo!!.serialize(encoder);
        } else {
            encoder.encodeBool(false);
        }
        return true
    }
}