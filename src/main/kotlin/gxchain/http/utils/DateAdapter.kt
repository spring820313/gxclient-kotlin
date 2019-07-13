package gxchain.http.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter {

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    init {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @FromJson
    internal fun fromJson(timestamp: String): Date {
        return dateFormatter.parse(timestamp)
    }

    @ToJson
    internal fun toJson(date: Date): String {
        return dateFormatter.format(date)
    }
}