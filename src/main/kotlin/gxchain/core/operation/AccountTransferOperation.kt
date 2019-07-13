package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*


class AccountTransferOperation(
    var fee: AssetAmount,
    var account_id: GrapheneId,
    var new_owner: GrapheneId,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AccountTransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account_id.serialize(encoder);
        this.new_owner.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}