package gxchain.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Witness (
    var id:String,
    var is_valid:Boolean,
    var total_votes:Int,
    var url:String,
    var vote_id:String,
    var last_aslot:Int,
    var last_confirmed_block_num:Int,
    var signing_key:String,
    var total_missed:Int,
    var witness_account:String
)