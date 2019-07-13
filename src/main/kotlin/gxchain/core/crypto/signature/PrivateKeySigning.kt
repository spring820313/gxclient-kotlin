
package gxchain.core.crypto.signature

import gxchain.core.base58.Base58Encode
import gxchain.core.crypto.GxcPrivateKey
import gxchain.core.hex.DefaultHexWriter

import org.bitcoinj.core.Sha256Hash
import org.spongycastle.asn1.x9.X9IntegerConverter

class PrivateKeySigning {

    private val x9 = X9IntegerConverter()

    fun sign(digest: ByteArray, eosPrivateKey: GxcPrivateKey): String {
        val signatureResult = ECSignatureProvider.sign(Sha256Hash.hash(digest), eosPrivateKey)
        return encodeSignature(signatureResult)
    }

    private fun encodeSignature(signatureResult: ECSignatureResult): String {
        if (signatureResult.recId < 0 || signatureResult.recId > 3) {
            throw IllegalStateException("Signature recovery id could not be retrieved, an invalid crypto was built.")
        }

        val sigData = ByteArray(65)

        val headerByte = signatureResult.recId + 27 + 4
        sigData[0] = headerByte.toByte()

        System.arraycopy(x9.integerToBytes(signatureResult.signature.r, 32), 0, sigData, 1, 32)
        System.arraycopy(x9.integerToBytes(signatureResult.signature.s, 32), 0, sigData, 33, 32)

        return DefaultHexWriter().bytesToHex(sigData)
    }
}