package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.ChainParameters
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class CommitteeMemberUpdateGlobalParametersOperation(
    var fee: AssetAmount,
    var new_parameters: ChainParameters):Operation() {
    override fun opTye(): Byte {
        return OpType.CommitteeMemberUpdateGlobalParametersOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.new_parameters.serialize(encoder);
        return true
    }
}