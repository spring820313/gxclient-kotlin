package gxchain.core.operation

import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.ChainParameters
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class CustomOperation(
    var fee: AssetAmount,
    var payer: GrapheneId,
    var required_auths: List<GrapheneId>,
    var id: Int,
    var data: String? = null
):Operation() {
    override fun opTye(): Byte {
        return OpType.CustomOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.payer.serialize(encoder);
        encoder.encodeVariableUInt(this.required_auths.size.toLong())
        for(x in this.required_auths) {
            x.serialize(encoder)
        }
        encoder.encodeShort(this.id.toShort());
        if(this.data == null) {
            encoder.encodeVariableUInt(0);
        } else {
            val hexBytes = DefaultHexWriter().hexToBytes(this.data!!)
            encoder.encodeVariableUInt(hexBytes.size.toLong());
            encoder.encodeBytes(hexBytes);
        }
        return true
    }
}