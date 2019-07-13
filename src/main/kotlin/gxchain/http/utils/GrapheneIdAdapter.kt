package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import gxchain.http.model.GrapheneId

class GrapheneIdAdapter {

    @FromJson
    internal fun fromJson(id: String): GrapheneId {
        return GrapheneId(id)
    }

    @ToJson
    internal fun toJson(grapheneId: GrapheneId): String {
        return grapheneId.toString()
    }
}