package gxchain.http.model

import gxchain.core.base58.Base58Decode
import gxchain.core.base58.Base58Encode
import gxchain.core.crypto.GxcPublicKey
import gxchain.core.hash.RIPEMD160Digest
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

class Address(var data:ByteArray, var checksum:ByteArray): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeBytes(this.data)
        return true
    }

    constructor(address:String):this(byteArrayOf(), byteArrayOf()) {
        if(!address.startsWith("GXC")) {
            return
        }
        val stripped = address.substring(3)
        try {
            val b58 = Base58Decode().decode(stripped)
            this.data = b58.bytes
            this.checksum = RIPEMD160Digest.hash(this.data).sliceArray(0..4)
        }catch (e:Exception) {

        }
    }

    constructor(pub:GxcPublicKey):this(byteArrayOf(), byteArrayOf()) {
        this.data = pub.bytes
        this.checksum = RIPEMD160Digest.hash(this.data).sliceArray(0..4)
    }

    override fun toString(): String {
        return Base58Encode().encodeKey("GXC", this.data)
    }
}