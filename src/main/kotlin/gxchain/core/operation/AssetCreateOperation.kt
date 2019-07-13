package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*


class AssetCreateOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var symbol: String,
    var precision: Int,
    var common_options: AssetOptions,
    var bitasset_opts: BitassetOptions? = null,
    var is_prediction_market: Boolean,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        encoder.encodeString(this.symbol);
        encoder.encodeByte(this.precision.toByte());
        this.common_options.serialize(encoder);
        encoder.encodeBool(this.bitasset_opts != null);
        if(this.bitasset_opts != null) {
            this.bitasset_opts!!.serialize(encoder);
        }
        encoder.encodeBool(this.is_prediction_market);
        encoder.encodeVariableUInt(0L)
        return true
    }
}