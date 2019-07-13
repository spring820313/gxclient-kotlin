package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import gxchain.http.model.Price

class AssetGlobalSettleOperation(
    var fee: AssetAmount,
    var issuer: GrapheneId,
    var asset_to_settle: GrapheneId,
    var settle_price: Price,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetGlobalSettleOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder);
        this.asset_to_settle.serialize(encoder);
        this.settle_price.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}