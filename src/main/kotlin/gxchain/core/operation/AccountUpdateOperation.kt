package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*

class AccountUpdateOperation(
    var fee:AssetAmount,
    var account:GrapheneId,
    var active:Authority? = null,
    var new_options:AccountOptions? = null,
    var owner:Authority? = null,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AccountUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account.serialize(encoder);
        encoder.encodeBool(this.owner != null);
        if(this.owner != null)
            this.owner!!.serialize(encoder);

        encoder.encodeBool(this.active != null);
        if(this.active != null)
            this.active!!.serialize(encoder);

        encoder.encodeBool(this.new_options != null);
        if(this.new_options != null)
            this.new_options!!.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}