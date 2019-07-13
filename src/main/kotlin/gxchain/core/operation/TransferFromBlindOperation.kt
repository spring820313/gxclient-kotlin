package gxchain.core.operation

import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.BlindInput
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class TransferFromBlindOperation(
    var fee: AssetAmount,
    var amount: AssetAmount,
    var to: GrapheneId,
    var blinding_factor: String,
    var inputs: List<BlindInput>):Operation() {
    override fun opTye(): Byte {
        return OpType.TransferFromBlindOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.amount.serialize(encoder);
        this.to.serialize(encoder);

        val codeBytes = DefaultHexWriter().hexToBytes(this.blinding_factor);
        encoder.encodeVariableUInt(codeBytes.size.toLong());
        encoder.encodeBytes(codeBytes);

        encoder.encodeVariableUInt(this.inputs.size.toLong());
        for(i in inputs) {
            i.serialize(encoder);
        }
        return true
    }
}