package gxchain.http

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity<T>(var id:Int, var result:T?, var error:Any?, var jsonrpc:String)