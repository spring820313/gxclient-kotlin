package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*


class BlindTransferOperation(
    var fee: AssetAmount,
    var inputs: List<BlindInput>,
    var outputs: List<BlindOutput>):Operation() {
    override fun opTye(): Byte {
        return OpType.BlindTransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        encoder.encodeVariableUInt(this.inputs.size.toLong());
        for(i in inputs) {
            i.serialize(encoder);
        }

        encoder.encodeVariableUInt(this.outputs.size.toLong());
        for(o in this.outputs) {
            o.serialize(encoder);
        }
        return true
    }
}