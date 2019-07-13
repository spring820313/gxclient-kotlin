package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.AssetAmount
import gxchain.http.model.GrapheneId
import gxchain.http.model.OpType


class InterContractCallOperation(
    var fee: AssetAmount,
    var sender_contract: GrapheneId,
    var contract_id: GrapheneId,
    var amount: AssetAmount,
    var method_name: String,
    var data: String,
    var extensions:List<Any> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.InterContractCallOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder);
        this.sender_contract.serialize(encoder);
        this.contract_id.serialize(encoder);
        this.amount.serialize(encoder);
        encoder.encodeString(this.method_name);
        encoder.encodeString(this.data);
        encoder.encodeVariableUInt(0);
        return true
    }
}