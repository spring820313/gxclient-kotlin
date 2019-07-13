package gxchain.core.operation

import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.http.model.*


class TransferToBlindOperation(
    var fee: AssetAmount,
    var amount: AssetAmount,
    var from: GrapheneId,
    var blinding_factor: String,
    var outputs: List<BlindOutput>):Operation() {
    override fun opTye(): Byte {
        return OpType.TransferToBlindOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.amount.serialize(encoder);
        this.from.serialize(encoder);

        val codeBytes = DefaultHexWriter().hexToBytes(this.blinding_factor);
        encoder.encodeVariableUInt(codeBytes.size.toLong());
        encoder.encodeBytes(codeBytes);

        encoder.encodeVariableUInt(this.outputs.size.toLong());
        for(o in outputs) {
            o.serialize(encoder);
        }
        return true
    }
}