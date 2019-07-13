package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

abstract class Operation:Serializable {
    abstract fun opTye(): Byte
}

class OperationTuple(var type:Byte, var op:Operation):Serializable{
    override fun serialize(encoder: Encoder):Boolean {
        this.op.serialize(encoder)
        return true
    }
}