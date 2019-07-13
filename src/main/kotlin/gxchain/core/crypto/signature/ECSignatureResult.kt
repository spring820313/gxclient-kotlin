
package gxchain.core.crypto.signature

import org.bitcoinj.core.ECKey

data class ECSignatureResult internal constructor(
    val signature: ECKey.ECDSASignature,
    val recId: Int
)