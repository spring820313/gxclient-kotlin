package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class BlindInput(var commitment:String, var owner:Authority): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        val codeHex = DefaultHexWriter().hexToBytes(this.commitment)
        encoder.encodeVariableUInt(codeHex.size.toLong())
        encoder.encodeBytes(codeHex);
        this.owner.serialize(encoder);
        return true
    }
}