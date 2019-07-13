package gxchain.core.operation

import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.http.model.Abi
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType

class CreateContractOperation(
    var fee:AssetAmount,
    var name:String,
    var account:GrapheneId,
    var vm_type:String,
    var vm_version:String,
    var code:String,
    var abi:Abi,
    val extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.CreateContractOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)

        encoder.encodeString(this.name);
        this.account.serialize(encoder);
        encoder.encodeString(this.vm_type);
        encoder.encodeString(this.vm_version);
        val codeBytes = DefaultHexWriter().hexToBytes(this.code);
        encoder.encodeVariableUInt(codeBytes.size.toLong());
        encoder.encodeBytes(codeBytes);
        this.abi.serialize(encoder);

        encoder.encodeVariableUInt(0L)
        return true
    }
}