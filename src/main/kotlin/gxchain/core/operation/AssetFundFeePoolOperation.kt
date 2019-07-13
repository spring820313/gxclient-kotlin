package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class AssetFundFeePoolOperation(
    var fee: AssetAmount,
    var from_account: GrapheneId,
    var asset_id: GrapheneId,
    var amount: Long,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssetFundFeePoolOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.from_account.serialize(encoder);
        this.asset_id.serialize(encoder);
        encoder.encodeLong(this.amount);
        encoder.encodeVariableUInt(0L)
        return true
    }
}