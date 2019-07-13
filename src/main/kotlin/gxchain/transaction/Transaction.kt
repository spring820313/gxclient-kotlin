package gxchain.transaction

import com.squareup.moshi.JsonClass
import gxchain.core.operation.Operation
import gxchain.core.operation.OperationTuple
import gxchain.encoder.Encoder
import java.util.*

@JsonClass(generateAdapter = true)
class Transaction(var ref_block_num:Long,
                  var ref_block_prefix:Long,
                  var expiration: Date,
                  var operations:List<OperationTuple> = listOf(),
                  var signatures:List<String> = listOf(),
                  var extensions:List<Any> = listOf()){
    fun serialize():ByteArray {
        val encoder = Encoder()
        encoder.encodeShort(this.ref_block_num.toShort())
        encoder.encodeInt(this.ref_block_prefix.toInt())
        encoder.encodeTimestampMs(this.expiration.time)
        val len = this.operations.size
        encoder.encodeVariableUInt(len.toLong())
        for(o in this.operations) {
            o.serialize(encoder)
        }
        encoder.encodeVariableUInt(0)
        return encoder.toBytes()
    }
}