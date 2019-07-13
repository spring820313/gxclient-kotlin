package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class AccountOptions(
    var memo_key:String,
    var voting_account:GrapheneId,
    var num_witness:Short,
    var num_committee:Short,
    var votes:List<VoteID> = mutableListOf(),
    var extensions:List<Any> = listOf()):Serializable {

    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodePubKey(this.memo_key)
        this.voting_account.serialize(encoder)
        encoder.encodeShort(this.num_witness)
        encoder.encodeShort(this.num_committee)
        encoder.encodeVariableUInt(this.votes.size.toLong())
        for(v in this.votes) {
            v.serialize(encoder)
        }
        encoder.encodeVariableUInt(0L)
        return true
    }
}