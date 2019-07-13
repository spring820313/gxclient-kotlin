package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AccountUpdateExtensions
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AccountUpgradeOperation(
    var fee: AssetAmount,
    var account_to_upgrade: GrapheneId,
    var upgrade_to_lifetime_member: Boolean,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AccountUpgradeOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account_to_upgrade.serialize(encoder);
        encoder.encodeBool(this.upgrade_to_lifetime_member);
        encoder.encodeVariableUInt(0L)
        return true
    }
}