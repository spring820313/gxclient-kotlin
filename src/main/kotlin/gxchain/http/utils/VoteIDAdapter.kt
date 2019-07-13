package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import gxchain.http.model.VoteID


class VoteIDAdapter {

    @FromJson
    internal fun fromJson(voteId: String): VoteID {
        return VoteID(voteId)
    }

    @ToJson
    internal fun toJson(voteId: VoteID): String {
        return voteId.toString()
    }
}