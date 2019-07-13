package gxchain.transaction

import gxchain.core.hex.DefaultHexWriter
import org.bitcoinj.core.Utils
import java.util.*

object TransactionUtils {
    fun refBlockNum(blockNumber:Int):Long {
        return (blockNumber and 0xFFFF).toLong()
    }

    fun refBlockPrefix(blockID:String):Long {
        return Utils.readUint32(DefaultHexWriter().hexToBytes(blockID.substring(8, 16)), 0)
    }

    fun defaultExpiry(t:Date, minutes:Int): Date = with(Calendar.getInstance()) {
        time = t
        set(Calendar.MINUTE, get(Calendar.MINUTE) + minutes)
        this
    }.time
}