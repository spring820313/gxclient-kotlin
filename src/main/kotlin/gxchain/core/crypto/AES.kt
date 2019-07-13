package gxchain.core.crypto

import gxchain.core.hash.Sha512Digest
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.ByteArrayBuffer
import org.bitcoinj.core.Sha256Hash
import org.spongycastle.crypto.engines.AESFastEngine
import org.spongycastle.crypto.modes.CBCBlockCipher
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher
import org.spongycastle.crypto.params.KeyParameter
import org.spongycastle.crypto.params.ParametersWithIV
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec



object AES {

    fun encryptAES(text: ByteArray, secretKey: ByteArray, initVec: ByteArray): ByteArray {

        var cipher = PaddedBufferedBlockCipher(CBCBlockCipher(AESFastEngine()))
        cipher.init(true, ParametersWithIV(KeyParameter(secretKey), initVec))
        var out = ByteArray(cipher.getOutputSize(text.size))
        val proc = cipher.processBytes(text, 0, text.size, out, 0)
        cipher.doFinal(out, proc)
        val ret = ByteArray(out.size - 16)
        out.copyInto(ret, 0, 0, ret.size)
        return out
    }

    fun decryptAES(cipherText: ByteArray, secretKey: ByteArray, initVec: ByteArray): String {

        var cipher = PaddedBufferedBlockCipher(CBCBlockCipher(AESFastEngine()))
        cipher.init(false, ParametersWithIV(KeyParameter(secretKey), initVec))

        var pre_out = ByteArray(cipher.getOutputSize(cipherText.size))
        val proc = cipher.processBytes(cipherText, 0, cipherText.size, pre_out, 0)
        val proc2 = cipher.doFinal(pre_out, proc)

        var out = ByteArray(proc + proc2)
        pre_out.copyInto(out, 0, 0, out.size)

        val count = (out[out.size - 1] % 16).toInt()
        if((count > 15) or (count <= 0)) {
            return String(out)
        }

        var temp = ByteArray(count)
        out.copyInto(temp, 0, out.size - count, count)
        var temp2 = ByteArray(count)
        temp2.fill(count.toByte())

        if(temp.equals(temp2)) {
            temp = ByteArray(out.size - count)
            out.copyInto(temp, 0, 0, out.size - count)
            return String(temp)
        }
        return String(out)

    }

    fun padArray(pad:Int, v:Byte):ByteArray {
        val bs = ByteArray(pad)
        bs.fill(v)
        return bs
    }

    fun encryptWithChecksum(privateKey:String, pubkey:String, nonce:String, message:String):ByteArray {
        val privKey = GxcPrivateKey(privateKey)
        val pubKey = GxcPublicKey(pubkey)
        val sharedSecret = privKey.sharedSecret(pubKey)

        val hexShared = DefaultHexWriter().bytesToHex(sharedSecret)
        var bab = ByteArrayBuffer(100)
        val nonceBytes = nonce.toByteArray()
        val sharedBytes = hexShared.toByteArray()

        bab.append(nonceBytes)
        bab.append(sharedBytes)
        val result = bab.toByteArray()

        val out = Sha512Digest.hash(result)
        val key = out.sliceArray(0..31)
        val iv = out.sliceArray(32..47)

        val messageBytes = message.toByteArray()
        val hd = Sha256Hash.hash(messageBytes)
        val checksum = hd.sliceArray(0..3)

        var ckBuf = ByteArrayBuffer(100)
        ckBuf.append(checksum)
        ckBuf.append(messageBytes)
        val len = ckBuf.length()
        if(len % 16 != 0) {
            val pad = 16 - len % 16
            val nPad = padArray(pad, pad.toByte())
            ckBuf.append(nPad)
        }

        val ret = encryptAES(ckBuf.toByteArray(), key, iv)
        //val org = decryptWithChecksum(privateKey, pubkey, nonce, ret)
        return ret
    }

    fun decryptWithChecksum(privateKey:String, pubkey:String, nonce:String, encryptedMessage:ByteArray):String {
        val privKey = GxcPrivateKey(privateKey)
        val pubKey = GxcPublicKey(pubkey)
        val sharedSecret = privKey.sharedSecret(pubKey)

        val hexShared = DefaultHexWriter().bytesToHex(sharedSecret)
        var bab = ByteArrayBuffer(100)
        val nonceBytes = nonce.toByteArray()
        val sharedBytes = hexShared.toByteArray()

        bab.append(nonceBytes)
        bab.append(sharedBytes)
        val result = bab.toByteArray()

        val out = Sha512Digest.hash(result)
        val key = out.sliceArray(0..31)
        val iv = out.sliceArray(32..47)

        //val encryptedData = DefaultHexWriter().hexToBytes(encryptedMessage)
        val ret = decryptAES(encryptedMessage, key, iv)

        //val checkcum = ret.substring(0..3)
        val msgData = ret.substring(4)
        return msgData
    }

}