package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import gxchain.http.model.WorkerInitializer
import java.util.*


class WorkerCreateOperation(
    var fee: AssetAmount,
    var owner: GrapheneId,
    var work_begin_date: Date,
    var work_end_date: Date,
    var daily_pay: Long,
    var name: String,
    var url: String,
    var initializer: WorkerInitializer):Operation() {
    override fun opTye(): Byte {
        return OpType.WorkerCreateOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.owner.serialize(encoder);
        encoder.encodeTimestampMs(this.work_begin_date.time)
        encoder.encodeTimestampMs(this.work_end_date.time)
        encoder.encodeLong(this.daily_pay);
        encoder.encodeString(this.name);
        encoder.encodeString(this.url);
        this.initializer.serialize(encoder);
        return true
    }
}