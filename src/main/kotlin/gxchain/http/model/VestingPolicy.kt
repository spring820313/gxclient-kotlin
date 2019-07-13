package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable
import java.util.*

@JsonClass(generateAdapter = true)
class CCDVestingPolicy(var start_claim: Date,
                       var coin_seconds_earned_last_update:Date,
                       var vesting_seconds:Int,
                       var coin_seconds_earned:Long
                       ): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeTimestampMs(this.start_claim.time)
        encoder.encodeTimestampMs(this.coin_seconds_earned_last_update.time)
        encoder.encodeInt(this.vesting_seconds);
        encoder.encodeLong(this.coin_seconds_earned);
        return true
    }
}

@JsonClass(generateAdapter = true)
class LinearVestingPolicy(var begin_timestamp:Date,
                          var vesting_cliff_seconds:Int,
                          var vesting_duration_seconds:Int
): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeTimestampMs(this.begin_timestamp.time)
        encoder.encodeInt(this.vesting_cliff_seconds);
        encoder.encodeInt(this.vesting_duration_seconds);
        return true
    }
}

class VestingPolicy(var type:VestingPolicyType, var data:Any):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(this.type.op)
        if(this.type == VestingPolicyType.VestingPolicyTypeLinear) {
            val p = this.data as LinearVestingPolicy
            p.serialize(encoder)
        } else if(this.type == VestingPolicyType.VestingPolicyTypeCCD) {
            val p = this.data as CCDVestingPolicy
            p.serialize(encoder)
        }
        return true
    }
}