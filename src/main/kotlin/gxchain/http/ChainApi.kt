
package gxchain.http

import gxchain.http.model.*
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChainApi {
    @POST("/")
    fun getChainId(@Body body:Call): Single<Response<Entity<String>>>

    @POST("/")
    fun getAccountByName(@Body body:Call): Single<Response<Entity<Account>>>

    @POST("/")
    fun getRequiredFee(@Body body:Call): Single<Response<Entity<List<AssetAmount>>>>

    @POST("/")
    fun getBlock(@Body body:Call): Single<Response<Entity<Block>>>

    @POST("/")
    fun getDynamicGlobalProperties(@Body body:Call): Single<Response<Entity<DynamicGlobalProperties>>>

    @POST("/")
    fun broadcastTransaction(@Body body:Call): Single<Response<Entity<Any>>>

    @POST("/")
    fun lookupAssetSymbols(@Body body:Call): Single<Response<Entity<List<Asset>>>>

    @POST("/")
    fun getContractAccountByName(@Body body:Call): Single<Response<Entity<ContractAccountProperties>>>

    @POST("/")
    fun getObjects(@Body body:Call): Single<Response<Entity<List<ObjectParameters>>>>

    @POST("/")
    fun serializeContractCallArgs(@Body body:Call): Single<Response<Entity<String>>>

    @POST("/")
    fun getTableRows(@Body body:Call): Single<Response<Entity<Any>>>

    @POST("/")
    fun getTableObjects(@Body body:Call): Single<Response<Entity<Any>>>

    @POST("/")
    fun getWitnessByAccount(@Body body:Call): Single<Response<Entity<Witness>>>

    @POST("/")
    fun getCommitteeMemberByAccount(@Body body:Call): Single<Response<Entity<Committee>>>

    @POST("/")
    fun getAccountBalances(@Body body:Call): Single<Response<Entity<List<AssetAmount>>>>

    @POST("/")
    fun getNamedAccountBalances(@Body body:Call): Single<Response<Entity<List<AssetAmount>>>>

    @POST("/")
    fun getAccountsByPublicKeys(@Body body:Call): Single<Response<Entity<List<List<String>>>>>

    @POST("account/register")
    fun register(@Body body:RegisterAccount): Single<Response<Any>>
}