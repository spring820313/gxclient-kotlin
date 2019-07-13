package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*

class AccountCreateOperation(
    var fee: AssetAmount,
    var registrar: GrapheneId,
    var referrer: GrapheneId,
    var referrer_percent: Int,
    var active: Authority,
    var owner: Authority,
    var name: String,
    var options:AccountOptions,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AccountCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.registrar.serialize(encoder);
        this.referrer.serialize(encoder);
        encoder.encodeShort(this.referrer_percent.toShort());
        this.owner.serialize(encoder);
        this.active.serialize(encoder);
        encoder.encodeString(this.name);
        this.options.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}