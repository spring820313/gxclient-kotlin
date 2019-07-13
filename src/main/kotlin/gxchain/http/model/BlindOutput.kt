package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class BlindOutput(var commitment:String, var range_proof:String, var owner:Authority, var stealth_memo:StealthConfirmation? = null): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        var codeHex = DefaultHexWriter().hexToBytes(this.commitment)
        encoder.encodeVariableUInt(codeHex.size.toLong())
        encoder.encodeBytes(codeHex);

        codeHex = DefaultHexWriter().hexToBytes(this.range_proof);
        encoder.encodeVariableUInt(codeHex.size.toLong());
        encoder.encodeBytes(codeHex);

        this.owner.serialize(encoder);
        encoder.encodeBool(this.stealth_memo != null);
        if(this.stealth_memo != null) {
            this.stealth_memo!!.serialize(encoder);
        }

        return true
    }
}