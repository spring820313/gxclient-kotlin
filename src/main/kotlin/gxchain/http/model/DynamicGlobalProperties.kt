package gxchain.http.model

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class DynamicGlobalProperties (
    val id:GrapheneId,
    val head_block_number:Long,
    val head_block_id:String,
    val time: Date,
    val current_witness:String,
    val next_maintenance_time:Date,
    val last_budget_time:String,
    val witness_budget:Long,
    val accounts_registered_this_interval:Long,
    val recently_missed_count:Long,
    val current_aslot:Long,
    val recent_slots_filled:String,
    val dynamic_flags:Int,
    val last_irreversible_block_num:Long
    )