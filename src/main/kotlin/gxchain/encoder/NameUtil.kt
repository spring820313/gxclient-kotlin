package gxchain.encoder

class NameUtil {
    fun encode(name: String, writer: Encoder) {
        writer.encodeLong(nameAsLong(name))
    }

    fun nameAsLong(name: String): Long {
        val len = name.length
        var value: Long = 0

        for (i in 0..MAX_NAME_IDX) {
            var c: Long = 0

            if (i < len && i <= MAX_NAME_IDX) {
                c = charToSymbol(name[i]).toLong()
            }

            if (i < MAX_NAME_IDX) {
                c = c and 0x1f
                c = c shl (64 - 5 * (i + 1))
            } else {
                c = c and 0x0f
            }

            value = value or c
        }

        return value
    }

    private fun charToSymbol(c: Char): Byte {
        if (c in 'a'..'z') {
            return (c - 'a' + 6).toByte()
        }
        return if (c in '1'..'5') (c - '1' + 1).toByte() else 0.toByte()
    }

    companion object {
        const val MAX_NAME_IDX = 12
    }
}