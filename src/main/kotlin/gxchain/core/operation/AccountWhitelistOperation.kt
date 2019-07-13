package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AccountUpdateExtensions
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AccountWhitelistOperation(
    var fee: AssetAmount,
    var account_to_list: GrapheneId,
    var authorizing_account: GrapheneId,
    var new_listing:Int,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AccountWhitelistOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account_to_list.serialize(encoder);
        this.authorizing_account.serialize(encoder);
        encoder.encodeByte(this.new_listing.toByte());
        encoder.encodeVariableUInt(0L)
        return true
    }
}