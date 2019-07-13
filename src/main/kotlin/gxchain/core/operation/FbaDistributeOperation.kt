package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.OpType


class FbaDistributeOperation(
    var fee: AssetAmount,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.FbaDistributeOperationOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)

        encoder.encodeVariableUInt(0)
        return true
    }
}