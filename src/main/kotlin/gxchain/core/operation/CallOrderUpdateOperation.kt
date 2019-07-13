package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class CallOrderUpdateOperation(
    var fee: AssetAmount,
    var funding_account: GrapheneId,
    var delta_collateral: AssetAmount,
    var delta_debt: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.CallOrderUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.funding_account.serialize(encoder);
        this.delta_collateral.serialize(encoder);
        this.delta_debt.serialize(encoder);
        encoder.encodeVariableUInt(0L)
        return true
    }
}