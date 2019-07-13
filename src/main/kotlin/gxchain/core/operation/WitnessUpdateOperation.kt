package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class WitnessUpdateOperation(
    var fee: AssetAmount,
    var witness: GrapheneId,
    var witness_account: GrapheneId,
    var new_url: String? = null,
    var new_signing_key: String? = null):Operation() {
    override fun opTye(): Byte {
        return OpType.WitnessUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.witness.serialize(encoder);
        this.witness_account.serialize(encoder);
        encoder.encodeBool(this.new_url != null);
        if(this.new_url != null)
            encoder.encodeString(this.new_url!!);
        encoder.encodeBool(this.new_signing_key != null);
        if(this.new_signing_key != null)
            encoder.encodePubKey(this.new_signing_key!!);
        return true
    }
}