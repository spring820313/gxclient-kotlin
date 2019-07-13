package gxchain.http.model

import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

class VoteID(var type:Int, var instance:Int):Serializable {

    constructor(vid:String):this(0, 0){
        val parts = vid.split(':')
        if(parts.size != 2) {
            return
        }
        this.type = parts[0].toInt()
        this.instance = parts[1].toInt()
    }

    override fun serialize(encoder: Encoder):Boolean {
        val bin:Int = (this.type and 0xff) or (this.instance shl 8)
        encoder.encodeInt(bin)
        return true
    }

    override fun toString(): String {
        return "${this.type}:${this.instance}"
    }
}