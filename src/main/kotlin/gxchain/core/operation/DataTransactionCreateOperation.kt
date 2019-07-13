package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import java.util.*


class DataTransactionCreateOperation(
    var fee: AssetAmount,
    var request_id: String,
    var product_id: GrapheneId,
    var version: String,
    var params: String,
    var requester: GrapheneId,
    var create_date_time: Date,
    var league_id: GrapheneId? = null,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.DataTransactionCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        encoder.encodeString(this.request_id);
        this.product_id.serialize(encoder);
        encoder.encodeString(this.version);
        encoder.encodeString(this.params);
        this.fee.serialize(encoder);
        this.requester.serialize(encoder);
        encoder.encodeTimestampMs(this.create_date_time.time)
        encoder.encodeBool(this.league_id != null);
        if(this.league_id != null)
            this.league_id!!.serialize(encoder);

        encoder.encodeVariableUInt(0);
        return true
    }
}