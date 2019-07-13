package gxchain.encoder

import gxchain.core.crypto.GxcPublicKey

class Encoder {
    private val buffer = ByteArrayBuffer(256)

    fun encodeByte(value: Byte) {
        buffer.append(value)
    }

    fun encodeBool(value: Boolean) {
        if(value) buffer.append(1.toByte())
        else buffer.append(0.toByte())
    }

    fun encodeShort(value: Short) {
        buffer.append(value)
    }

    fun encodeInt(value: Int) {
        buffer.append(value)
    }

    fun encodeVariableUInt(value: Long) {
        var v: Long = value
        while (v >= 0x80) {
            val b = ((v and 0x7f) or 0x80).toByte()
            buffer.append(b)
            v = v ushr 7
        }
        buffer.append(v.toByte())
    }

    fun encodeLong(value: Long) {
        buffer.append(value)
    }

    fun encodeFloat(value: Float) {
        buffer.append(value)
    }

    fun encodeBytes(value: ByteArray) {
        buffer.append(value)
    }

    fun encodeString(value: String) {
        val bytes = value.toByteArray()
        encodeVariableUInt(bytes.size.toLong())
        buffer.append(value.toByteArray())
    }

    fun encodeStringCollection(stringList: List<String>) {
        encodeVariableUInt(stringList.size.toLong())

        if (stringList.isNotEmpty()) {
            for (string in stringList) {
                encodeString(string)
            }
        }
    }

    fun encodeName(value: String) {
        NameUtil().encode(value, this)
    }

    fun encodePubKey(pubkey: String) {
        val p = GxcPublicKey(pubkey)
        encodeBytes(p.bytes)
    }

    fun encodeTimestampMs(value: Long) {
        encodeInt((value / 1000).toInt())
    }

    fun toBytes(): ByteArray = buffer.toByteArray()

}