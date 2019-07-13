package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.AssetOptions
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetUpdateOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var new_issuer: GrapheneId? = null,
    var asset_to_update: GrapheneId,
    var new_options: AssetOptions,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        this.asset_to_update.serialize(encoder);
        encoder.encodeBool(this.new_issuer != null);
        if(this.new_issuer != null)
            this.new_issuer!!.serialize(encoder);
        this.new_options.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}