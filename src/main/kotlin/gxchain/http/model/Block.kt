package gxchain.http.model

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Block (val transaction_merkle_root:String,
                  val previous:String,
                  val timestamp: Date,
                  val witness:String,
                  val witness_signature:String
                  )