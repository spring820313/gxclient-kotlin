package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class WitnessCreateOperation(
    var fee: AssetAmount,
    var witness_account: GrapheneId,
    var url: String,
    var block_signing_key: String):Operation() {
    override fun opTye(): Byte {
        return OpType.WitnessCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.witness_account.serialize(encoder);
        encoder.encodeString(this.url);
        encoder.encodePubKey(this.block_signing_key);
        return true
    }
}