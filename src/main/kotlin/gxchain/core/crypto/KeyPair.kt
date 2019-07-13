package gxchain.core.crypto

import com.develop.mnemonic.MnemonicUtils
import org.bitcoinj.core.Sha256Hash

class KeyPair(var brainKey:String, var privateKey:String, var publicKey:String) {
    constructor(seed:String? = null):this("", "", "") {
        if(seed == null) {
            this.brainKey = MnemonicUtils.generateMnemonic()
        } else {
            this.brainKey = seed
        }
        val privateKeyBytes = Sha256Hash.hash(this.brainKey.toByteArray())
        val priKey = GxcPrivateKey(privateKeyBytes)
        this.privateKey = priKey.toString()
        this.publicKey = priKey.publicKey.toString()
    }

 }