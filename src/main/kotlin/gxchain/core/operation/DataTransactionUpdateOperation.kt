package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class DataTransactionUpdateOperation(
    var fee: AssetAmount,
    var request_id: String,
    var new_status: Int,
    var new_requester: GrapheneId,
    var memo: String,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.DataTransactionUpdateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        encoder.encodeString(this.request_id)
        encoder.encodeByte(this.new_status.toByte())
        this.fee.serialize(encoder)
        this.new_requester.serialize(encoder)
        encoder.encodeString(this.memo)

        encoder.encodeVariableUInt(0)
        return true
    }
}