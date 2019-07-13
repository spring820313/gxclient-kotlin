package gxchain.core.hash

class Sha512Digest {
    companion object {
        fun hash(data: ByteArray): ByteArray {
            val digest = org.spongycastle.crypto.digests.SHA512Digest()
            digest.update(data, 0, data.size)
            val out = ByteArray(64)
            digest.doFinal(out, 0)
            return out
        }
    }
}