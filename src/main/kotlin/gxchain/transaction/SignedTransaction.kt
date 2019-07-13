package gxchain.transaction

import gxchain.core.crypto.GxcPrivateKey
import gxchain.core.crypto.signature.PrivateKeySigning
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.ByteArrayBuffer
import org.bitcoinj.core.Sha256Hash

class SignedTransaction(val tx:Transaction) {
    fun digest(chainId:String):ByteArray {
        val idBytes = DefaultHexWriter().hexToBytes(chainId)
        val bab = ByteArrayBuffer(100)
        bab.append(idBytes)
        val txBytes = tx.serialize()
        bab.append(txBytes)

        val result = bab.toByteArray()
        val hex = DefaultHexWriter().bytesToHex(result)
        println(hex)

        return result
    }

    fun sign(wifs:List<String>, chainId: String):Boolean {
        if(wifs.size < 1) {
            return false
        }
        val hash = this.digest(chainId)
        val hex = DefaultHexWriter().bytesToHex(hash)
        println(hex)

        var sigsHex = mutableListOf<String>()
        for(wif in wifs) {
            val privateKey = GxcPrivateKey(wif)
            val signature = PrivateKeySigning().sign(hash, privateKey)
            sigsHex.add(signature)
        }
        this.tx.signatures = sigsHex
        return true
    }
}