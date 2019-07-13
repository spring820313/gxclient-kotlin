package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
data class ChainParameters (
    var current_fees:FeeSchedule,
    var maximum_committee_count:Short,
    var maximum_witness_count:Short,
    var block_interval:Byte,
    var maintenance_interval:Int,
    var maintenance_skip_slots:Byte,
    var committee_proposal_review_period:Int,
    var maximum_transaction_size:Int,
    var maximum_block_size:Int,
    var maximum_time_until_expiration:Int,

    var maximum_proposal_lifetime:Int,
    var maximum_asset_whitelist_authorities:Byte,
    var maximum_asset_feed_publishers:Byte,
    var maximum_authority_membership:Short,
    var reserve_percent_of_fee:Short,
    var network_percent_of_fee:Short,

    var lifetime_referrer_percent_of_fee:Short,
    var cashback_vesting_period_seconds:Int,
    var cashback_vesting_threshold:Long,
    var count_non_member_votes:Boolean,
    var allow_non_member_whitelists:Boolean,
    var witness_pay_per_block:Long,

    var worker_budget_per_day:Long,
    var max_predicate_opcode:Short,
    var fee_liquidation_threshold:Long,
    var accounts_per_fee_scale:Short,
    var account_fee_scale_bitshifts:Byte,
    var max_authority_depth:Byte,
    var extensions:List<Any> = listOf()

): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        this.current_fees.serialize(encoder);
        encoder.encodeByte(this.block_interval);
        encoder.encodeInt(this.maintenance_interval);
        encoder.encodeByte(this.maintenance_skip_slots);

        encoder.encodeInt(this.committee_proposal_review_period);
        encoder.encodeInt(this.maximum_transaction_size);
        encoder.encodeInt(this.maximum_block_size);
        encoder.encodeInt(this.maximum_time_until_expiration);
        encoder.encodeInt(this.maximum_proposal_lifetime);
        encoder.encodeByte(this.maximum_asset_whitelist_authorities);

        encoder.encodeByte(this.maximum_asset_feed_publishers);
        encoder.encodeShort(this.maximum_witness_count);
        encoder.encodeShort(this.maximum_committee_count);
        encoder.encodeShort(this.maximum_authority_membership);
        encoder.encodeShort(this.reserve_percent_of_fee);
        encoder.encodeShort(this.network_percent_of_fee);

        encoder.encodeShort(this.lifetime_referrer_percent_of_fee);
        encoder.encodeInt(this.cashback_vesting_period_seconds);
        encoder.encodeLong(this.cashback_vesting_threshold);
        encoder.encodeBool(this.count_non_member_votes);
        encoder.encodeBool(this.allow_non_member_whitelists);
        encoder.encodeLong(this.witness_pay_per_block);

        encoder.encodeLong(this.worker_budget_per_day);
        encoder.encodeShort(this.max_predicate_opcode);
        encoder.encodeLong(this.fee_liquidation_threshold);
        encoder.encodeShort(this.accounts_per_fee_scale);
        encoder.encodeByte(this.account_fee_scale_bitshifts);
        encoder.encodeByte(this.max_authority_depth);

        encoder.encodeVariableUInt(0);
        return true
    }
}

@JsonClass(generateAdapter = true)
data class ObjectParameters (
    var id:GrapheneId,
    var parameters:ChainParameters
)
