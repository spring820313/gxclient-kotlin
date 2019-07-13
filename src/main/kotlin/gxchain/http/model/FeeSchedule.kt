package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
data class FeeSchedule(
    var parameters:List<FeeParameters>,
    var scale:Long): Serializable {
    override fun serialize(encoder: Encoder): Boolean {
        encoder.encodeVariableUInt(this.parameters.size.toLong())
        for(p in this.parameters) {
            p.serialize(encoder)
        }
        encoder.encodeInt(this.scale.toInt())
        return true
    }
}