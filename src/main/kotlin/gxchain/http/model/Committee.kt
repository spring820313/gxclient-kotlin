package gxchain.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Committee (
    var id:String,
    var is_valid:Boolean,
    var total_votes:Int,
    var url:String,
    var vote_id:String
)