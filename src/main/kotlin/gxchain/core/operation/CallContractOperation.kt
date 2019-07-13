package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AccountUpdateExtensions
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType

class CallContractOperation(
    var fee:AssetAmount,
    var account:GrapheneId,
    var contract_id:GrapheneId,
    var method_name:String,
    var data:String,
    var amount:AssetAmount? = null,
    val extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.CallContractOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.account.serialize(encoder)
        this.contract_id.serialize(encoder)
        encoder.encodeBool(this.amount != null)
        if(this.amount != null)
            this.amount!!.serialize(encoder)

        encoder.encodeName(this.method_name)
        encoder.encodeString(this.data);

        encoder.encodeVariableUInt(0L)
        return true
    }
}