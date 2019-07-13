package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.Memo
import gxchain.http.model.OpType

class TransferOperation(
    val from:GrapheneId,
    val to:GrapheneId,
    val amount:AssetAmount,
    val fee:AssetAmount,
    val memo:Memo? = null,
    val extensions:List<Any> = listOf()):Operation() {

    override fun opTye(): Byte {
        return OpType.TransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.from.serialize(encoder)
        this.to.serialize(encoder)
        this.amount.serialize(encoder)
        encoder.encodeBool(this.memo != null)
        if(this.memo != null) {
            this.memo.serialize(encoder)
        }
        encoder.encodeVariableUInt(0L)
        return true
    }

}