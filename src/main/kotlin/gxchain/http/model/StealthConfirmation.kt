package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class StealthConfirmation(var one_time_key:String, var encrypted_memo:String, var to:String? = null): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodePubKey(this.one_time_key);
        encoder.encodeBool(this.to != null);
        if(this.to != null) {
            encoder.encodePubKey(this.to!!);
        }
        val codeHex = DefaultHexWriter().hexToBytes(this.encrypted_memo);
        encoder.encodeVariableUInt(codeHex.size.toLong());
        encoder.encodeBytes(codeHex);
        return true
    }
}