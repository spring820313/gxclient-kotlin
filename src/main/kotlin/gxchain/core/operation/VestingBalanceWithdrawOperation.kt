package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType



class VestingBalanceWithdrawOperation(
    var fee: AssetAmount,
    var vesting_balance: GrapheneId,
    var owner: GrapheneId,
    var amount: AssetAmount):Operation() {
    override fun opTye(): Byte {
        return OpType.VestingBalanceWithdrawOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.vesting_balance.serialize(encoder);
        this.owner.serialize(encoder);
        this.amount.serialize(encoder);
        return true
    }
}