package gxchain.http

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Call(var id:Int, var method:String, var params:List<Any> = listOf(), var jsonrpc:String = "2.0")