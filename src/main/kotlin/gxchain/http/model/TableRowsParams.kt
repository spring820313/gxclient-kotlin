package gxchain.http.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TableRowsParams (
    var lower_bound:Int,
    var upper_bound:Int,
    var index_position:Int,
    var limit:Int,
    var reverse:Boolean
)