package gxchain.core.operation

import com.squareup.moshi.JsonClass
import gxchain.core.crypto.GxcPrivateKey
import gxchain.core.crypto.signature.PrivateKeySigning
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType
import java.util.*

@JsonClass(generateAdapter = true)
class SignedProxyTransferParams(
    var from:GrapheneId,
    var to:GrapheneId,
    var proxy_account:GrapheneId,
    var amount:AssetAmount,
    var percentage:Int,
    var memo:String,
    var expiration:Date,
    var signatures:List<String> = listOf()
): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        val unsigned = toUnsignBytes(true);
        encoder.encodeBytes(unsigned);

        val sigSize = this.signatures.size;
        encoder.encodeVariableUInt(sigSize.toLong());
        for(s in this.signatures) {
            val sig = DefaultHexWriter().hexToBytes(s);
            encoder.encodeBytes(sig);
        }

        return true;
    }

    fun toUnsignBytes(signed:Boolean):ByteArray {
        val encoder = Encoder()
        this.from.serialize(encoder);
        this.to.serialize(encoder);
        this.proxy_account.serialize(encoder);
        this.amount.serialize(encoder);
        encoder.encodeShort(this.percentage.toShort());
        encoder.encodeString(this.memo);
        encoder.encodeTimestampMs(this.expiration.time);
        if(signed == false)
            encoder.encodeVariableUInt(0L);
        return encoder.toBytes()
    }

    fun sign(key:String) {
        val msgBytes = toUnsignBytes(false);

        var sigsHex = mutableListOf<String>()
        val privateKey = GxcPrivateKey(key)
        val signature = PrivateKeySigning().sign(msgBytes, privateKey)
        sigsHex.add(signature)

        this.signatures = sigsHex
    }
}

class ProxyTransferOperation(
    var fee: AssetAmount,
    var proxy_memo:String,
    var request_params:SignedProxyTransferParams,
    val extensions:List<Any> = listOf()):Operation(){
    override fun opTye(): Byte {
        return OpType.ProxyTransferOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        encoder.encodeString(this.proxy_memo);
        this.fee.serialize(encoder)
        this.request_params.serialize(encoder);

        encoder.encodeVariableUInt(0L)
        return true
    }
}