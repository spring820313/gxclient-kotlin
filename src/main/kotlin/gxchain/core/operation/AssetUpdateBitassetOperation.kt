package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.AssetOptions
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetUpdateBitassetOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var asset_to_update: GrapheneId,
    var new_options: AssetOptions,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetUpdateBitassetOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        this.asset_to_update.serialize(encoder);
        this.new_options.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}