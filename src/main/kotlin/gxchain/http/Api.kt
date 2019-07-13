
package gxchain.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import gxchain.http.utils.*
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    moshi: Moshi = Moshi.Builder()
        .add(DateAdapter())
        .add(GrapheneIdAdapter())
        .add(GxcPublicKeyAdapter())
        .add(AccountAuthsMapAdapter.FACTORY)
        .add(KeyAuthsMapAdapter.FACTORY)
        .add(AddressAuthsMapAdapter.FACTORY)
        .add(VoteIDAdapter())
        .add(ActiveSpecialAuthorityAdapter.FACTORY)
        .add(OwnerSpecialAuthorityAdapter.FACTORY)
        .add(OperationTupleAdapter.FACTORY)
        .build(),
    converterFactory: Converter.Factory = MoshiConverterFactory.create(moshi),
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build(),
    val chain: ChainApi = retrofit.create(ChainApi::class.java),
    val history: HistoryApi = retrofit.create(HistoryApi::class.java)
)