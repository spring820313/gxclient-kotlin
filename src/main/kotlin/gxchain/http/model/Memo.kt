package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.crypto.AES
import gxchain.core.crypto.GxcPrivateKey
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable
import java.util.*
import kotlin.random.Random

@JsonClass(generateAdapter = true)
class Memo(var from:String, var to:String, var nonce:Long, var message:String? = null):Serializable {

    constructor(fromPrivKey:String, to:String, utf8Msg:String? = null):this("", to, 0, null){
        this.nonce = randNonce()
        var privKey = GxcPrivateKey(fromPrivKey)
        this.from = privKey.publicKey.toString()
        if(utf8Msg != null && utf8Msg.length > 0) {
            val encryptedData = AES.encryptWithChecksum(fromPrivKey, to, nonce.toString(), utf8Msg)
            this.message = DefaultHexWriter().bytesToHex(encryptedData)
        }
    }

    fun randNonce():Long {
        return Random(Date().time).nextLong()
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodePubKey(from);
        encoder.encodePubKey(to);
        encoder.encodeLong(nonce);
        if(this.message != null && this.message?.length!! > 0) {
            var msgData = DefaultHexWriter().hexToBytes(this.message!!);
            encoder.encodeVariableUInt(msgData.size.toLong());
            if(msgData.size > 0) {
                encoder.encodeBytes(msgData);
            }
        } else {
            encoder.encodeVariableUInt(0L);
        }

        return true
    }
}