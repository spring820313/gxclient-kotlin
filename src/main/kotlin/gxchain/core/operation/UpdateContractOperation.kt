package gxchain.core.operation

import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.http.model.Abi
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType

class UpdateContractOperation(
    var fee: AssetAmount,
    var owner:GrapheneId,
    var new_owner:GrapheneId? = null,
    var contract:GrapheneId,
    var code:String,
    var abi:Abi,
    val extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.UpdateContractOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)

        this.owner.serialize(encoder);
        encoder.encodeBool(this.new_owner != null);
        if(this.new_owner != null)
            this.new_owner!!.serialize(encoder);
        this.contract.serialize(encoder);
        val codeBytes = DefaultHexWriter().hexToBytes(this.code);
        encoder.encodeVariableUInt(codeBytes.size.toLong());
        encoder.encodeBytes(codeBytes);
        this.abi.serialize(encoder);

        encoder.encodeVariableUInt(0L)
        return true
    }
}