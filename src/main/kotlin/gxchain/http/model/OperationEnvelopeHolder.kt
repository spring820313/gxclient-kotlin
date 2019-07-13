package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.operation.OperationTuple
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class OperationEnvelopeHolder(var op:OperationTuple): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        return op.serialize(encoder)
    }
}