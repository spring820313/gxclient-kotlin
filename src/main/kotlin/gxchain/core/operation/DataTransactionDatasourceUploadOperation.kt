package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import java.util.*


class DataTransactionDatasourceUploadOperation(
    var fee: AssetAmount,
    var request_id: String,
    var requester: GrapheneId,
    var datasource: GrapheneId,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.DataTransactionDatasourceUploadOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        encoder.encodeString(this.request_id)
        this.requester.serialize(encoder)
        this.datasource.serialize(encoder)
        this.fee.serialize(encoder)

        encoder.encodeVariableUInt(0)
        return true
    }
}