package gxchain.http.model

import gxchain.core.base58.Base58Encode
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

class GrapheneId(var space:Int, var type:Int, var instance:Int):Serializable {
    constructor(gid:String):this(0, 0, 0){
        val parts = gid.split('.')
        if(parts.size != 3) {
            return
        }
        this.space = parts[0].toInt()
        this.type = parts[1].toInt()
        this.instance = parts[2].toInt()
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.instance.toLong())
        return true
    }

    override fun toString(): String {
        return "${this.space}.${this.type}.${this.instance}"
    }
}