package gxchain.core.operation

import gxchain.encoder.Encoder
import gxchain.http.model.*

class AssertOperation(
    var fee: AssetAmount,
    var fee_paying_account: GrapheneId,
    var predicates: List<Predicate>,
    var required_auths: List<GrapheneId>,
    var extensions:List<AccountUpdateExtensions> = listOf()):Operation() {
    override fun opTye(): Byte {
        return OpType.AssertOpType.op
    }

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(opTye().toLong())
        this.fee.serialize(encoder)
        this.fee_paying_account.serialize(encoder);
        encoder.encodeVariableUInt(this.predicates.size.toLong());
        for(p in this.predicates) {
            p.serialize(encoder);
        }
        encoder.encodeVariableUInt(this.required_auths.size.toLong());
        for(r in this.required_auths) {
            r.serialize(encoder);
        }
        encoder.encodeVariableUInt(0L)
        return true
    }
}