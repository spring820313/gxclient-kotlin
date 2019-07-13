package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable
import java.util.*

@JsonClass(generateAdapter = true)
class Account(
    var id:GrapheneId,
    var name:String,
    var statistics:GrapheneId,
    var membership_expiration_date: Date,
    var network_fee_percentage:Long,
    var lifetime_referrer_fee_percentage:Long,
    var referrer_rewards_percentage:Long,
    var top_n_control_flags:Long,
    var whitelisting_accounts:List<GrapheneId> = listOf(),
    var blacklisting_accounts:List<GrapheneId> = listOf(),
    var whitelisted_accounts:List<GrapheneId> = listOf(),
    var blacklisted_accounts:List<GrapheneId> = listOf(),
    var options:AccountOptions,
    var registrar:GrapheneId,
    var referrer:GrapheneId,
    var lifetime_referrer:GrapheneId,
    var owner:Authority,
    var active:Authority,
    var owner_special_authority:OwnerSpecialAuthority,
    var active_special_authority:ActiveSpecialAuthority
    ):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        return true
    }
}