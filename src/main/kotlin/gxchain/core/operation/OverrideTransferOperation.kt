package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.Memo
import gxchain.http.model.OpType


class OverrideTransferOperation(
    var fee: AssetAmount,
    var from: GrapheneId,
    var to: GrapheneId,
    var amount: AssetAmount,
    var issuer: GrapheneId,
    var memo: Memo? = null,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.OverrideTransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.issuer.serialize(encoder)
        this.from.serialize(encoder)
        this.to.serialize(encoder)
        this.amount.serialize(encoder)
        if(this.memo != null) {
            encoder.encodeBool(true)
            this.memo!!.serialize(encoder)
        } else {
            encoder.encodeBool(false)
        }
        encoder.encodeVariableUInt(0)
        return true
    }
}