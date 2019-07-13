package gxchain

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import gxchain.core.operation.*
import gxchain.http.Api
import gxchain.http.Call
import gxchain.http.model.*
import gxchain.http.utils.*
import gxchain.transaction.SignedTransaction
import gxchain.transaction.Transaction
import gxchain.transaction.TransactionUtils
import okhttp3.OkHttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

class Client(val url:String) {
    val okHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    var chainID:String? = ""
    val chainApi = Api(url, okHttpClient).chain

    fun startup():Boolean {
        this.chainID = getChainId()
        this.chainID ?: return false
        return true
    }

    fun getChainId(): String? {
        val chainIdCall = Call(Id++, "get_chain_id", listOf());
        val chainId = chainApi.getChainId(chainIdCall)
        var t = chainId.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getAccountByName(name:String): Account? {
        val call = Call(Id++, "get_account_by_name", listOf(name));
        val ret = chainApi.getAccountByName(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getContractAccountByName(name:String): ContractAccountProperties? {
        val call = Call(Id++, "get_account_by_name", listOf(name));
        val ret = chainApi.getContractAccountByName(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getContractABI(name:String): Abi? {
        val ret = getContractAccountByName(name)
        return ret?.abi
    }

    fun getContractTable(name:String): List<Table>? {
        val ret = getContractABI(name)
        return ret?.tables
    }

    fun getAccounts(names:List<String>): List<Account?> {
        var accounts = mutableListOf<Account?>()
        for(n in names) {
            val account = getAccountByName(n)
            accounts.add(account)
        }
        return accounts
    }

    fun getRequiredFee(assetID:String, operations:List<OperationTuple>): List<AssetAmount>? {
        val call = Call(Id++, "get_required_fees", listOf(operations, assetID));
        try {
            val ret = chainApi.getRequiredFee(call)
            var t = ret.blockingGet()
            if(t.isSuccessful) {
                return t.body()?.result
            }
        }catch (e: JsonDataException) {
           e.printStackTrace()
        }

        return null;
    }

    fun getBlock(blockNum:Int): Block? {
        val call = Call(Id++, "get_block", listOf(blockNum));
        val ret = chainApi.getBlock(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getDynamicGlobalProperties(): DynamicGlobalProperties? {
        val call = Call(Id++, "get_dynamic_global_properties", listOf());
        val ret = chainApi.getDynamicGlobalProperties(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun lookupAssetSymbols(names:List<String>): List<Asset>? {
        val call = Call(Id++, "lookup_asset_symbols", listOf(names));
        val ret = chainApi.lookupAssetSymbols(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getObjects(objIds:List<String>): List<ObjectParameters>? {
        val call = Call(Id++, "get_objects", listOf(objIds));
        try {
            val ret = chainApi.getObjects(call)
            var t = ret.blockingGet()
            if(t.isSuccessful) {
                return t.body()?.result
            }
        }catch (e: JsonDataException) {
            e.printStackTrace()
        }

        return null;
    }

    fun serializeContractCallArgs(contractName:String, method:String, parameters:Any?): String? {
        val moshi = Moshi.Builder().build()
        var param = "{}"
        if(parameters != null)
            param = moshi.adapter(Any::class.java).toJson(parameters)
        val call = Call(Id++, "serialize_contract_call_args", listOf(contractName, method, param));
        val ret = chainApi.serializeContractCallArgs(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getTableRows(contractName:String, tableName:String, lowerBound:Int, upperBound:Int): Any? {
        val call = Call(Id++, "get_table_rows", listOf(contractName, tableName, lowerBound, upperBound));
        val ret = chainApi.getTableRows(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getTableObjects(contractName:String, tableName:String, params:TableRowsParams): Any? {
        val call = Call(Id++, "get_table_rows_ex", listOf(contractName, tableName, params));
        val ret = chainApi.getTableObjects(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getWitnessByAccount(accountId:String): Witness? {
        val call = Call(Id++, "get_witness_by_account", listOf(accountId));
        val ret = chainApi.getWitnessByAccount(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getCommitteeMemberByAccount(accountId:String): Committee? {
        val call = Call(Id++, "get_committee_member_by_account", listOf(accountId));
        val ret = chainApi.getCommitteeMemberByAccount(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun getAccountBalances(accountId:String, assetIDs:List<String>): List<AssetAmount>? {
        val call = Call(Id++, "get_account_balances", listOf(accountId, assetIDs));
        try {
            val ret = chainApi.getAccountBalances(call)
            var t = ret.blockingGet()
            if(t.isSuccessful) {
                return t.body()?.result
            }
        }catch (e: JsonDataException) {
            e.printStackTrace()
        }

        return null;
    }

    fun getNamedAccountBalances(account:String, assetIDs:List<String>): List<AssetAmount>? {
        val call = Call(Id++, "get_named_account_balances", listOf(account, assetIDs));
        try {
            val ret = chainApi.getNamedAccountBalances(call)
            var t = ret.blockingGet()
            if(t.isSuccessful) {
                return t.body()?.result
            }
        }catch (e: JsonDataException) {
            e.printStackTrace()
        }

        return null;
    }

    fun getVoteIdsByAccounts(accountNames:List<String>): List<String> {
        var ids = mutableListOf<String>()

        val accounts = getAccounts(accountNames)

        for(account in accounts) {
            val wi = getWitnessByAccount(account!!.id.toString())
            if(wi != null) {
                ids.add(wi.vote_id)
            }

            val co = getCommitteeMemberByAccount(account.id.toString())
            if(co != null) {
                ids.add(co.vote_id)
            }
        }

        return ids;
    }

    fun getAccountsByPublicKeys(publicKeys:List<String>): List<List<String>>? {
        val call = Call(Id++, "get_key_references", listOf(publicKeys));
        try {
            val ret = chainApi.getAccountsByPublicKeys(call)
            var t = ret.blockingGet()
            if(t.isSuccessful) {
                return t.body()?.result
            }
        }catch (e: JsonDataException) {
            e.printStackTrace()
        }

        return null;
    }

    fun broadcastTransaction(tx:Transaction): Any? {
        val call = Call(Id++, "call", listOf(2, "broadcast_transaction", listOf<Any>(tx)));

        val moshi: Moshi = Moshi.Builder()
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
            .build()
        val s2 = moshi.adapter(List::class.java).toJson(listOf(2, "broadcast_transaction", listOf<Any>(tx)))
        println(s2)

        val ret = chainApi.broadcastTransaction(call)
        var t = ret.blockingGet()
        if(t.isSuccessful) {
            return t.body()?.result
        }
        return null;
    }

    fun sign(wifs:List<String> , operations:List<OperationTuple>):SignedTransaction? {
        val props = getDynamicGlobalProperties()
        props ?: throw Exception("failed to get dynamic global properties")
        val block = getBlock(props.last_irreversible_block_num.toInt())
        block ?: throw Exception("failed to get block")

        val refBlockPrefix = TransactionUtils.refBlockPrefix(block.previous)
        val expiration = TransactionUtils.defaultExpiry(props.time, 10)
        val refBlockNum = TransactionUtils.refBlockNum((props.last_irreversible_block_num - 1).toInt() and 0xffff)

        var tx = Transaction(refBlockNum, refBlockPrefix, expiration, operations)
        val stx = SignedTransaction(tx)
        val ret = stx.sign(wifs, chainID!!)
        if(!ret) return null

        return stx
    }

    fun transfer(key:String, from:GrapheneId, to:GrapheneId, amount:AssetAmount, fee:AssetAmount, memo: Memo?):Any? {
        var op = TransferOperation(from, to, amount, fee, memo)
        val assetID = fee.asset_id.toString()

        val assets = getRequiredFee(assetID, listOf(OperationTuple(op.opTye(),op)))
        if((assets == null)) {
            return null
        }
        if(assets.isEmpty()) {
            return null
        }
        op.fee.amount = assets[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }

    fun vote(key:String, from:String, accounts:List<String>, proxyAccount:String? = null, feeAsset:String = "GXC"):Any? {
        var myAccount = getAccountByName(from)
        myAccount ?: throw Exception("Bad Parameter ")
        val myAccountId = myAccount.id

        var votingAccountId:GrapheneId?
        if(proxyAccount == null) {
            votingAccountId = GrapheneId("1.2.5")
        } else {
            val votingAccount = getAccountByName(proxyAccount)
            votingAccount ?: throw Exception("Bad Parameter ")
            votingAccountId = votingAccount.id
        }

        var voteIds = getVoteIdsByAccounts(accounts)
        var assets = lookupAssetSymbols(listOf(feeAsset))
        assets ?: throw Exception("Bad Parameter ")

        if(assets.isEmpty()) {
            throw Exception("Bad Parameter ")
        }
        val feeAssetId = assets[0].id

        val objs = getObjects(listOf("2.0.0"))
        objs ?: throw Exception("Exception occurred ")

        if(objs.isEmpty()) {
            throw Exception("Exception occurred ")
        }

        val maximumCommitteeCount = objs[0].parameters.maximum_committee_count
        val maximumWitnessCount = objs[0].parameters.maximum_witness_count

        var newOptions = myAccount.options
        var varVotes = newOptions.votes.toMutableList()
        for(voteId in voteIds) {
            val vote = VoteID(voteId)
            varVotes.add(vote)
        }
        newOptions.votes = varVotes.sortedBy { it.instance }.distinctBy { it.toString()}.toMutableList()

        var numCommitee = 0;
        var numWitness = 0;
        for(voteId in newOptions.votes) {
            val typ = voteId.type;
            if(typ == 0) {
                numCommitee += 1;
            }
            if(typ == 1) {
                numWitness += 1;
            }
        }
        if(numWitness < maximumWitnessCount) {
            newOptions.num_witness = numWitness.toShort()
        } else {
            newOptions.num_witness = maximumWitnessCount
        }

        if(numCommitee < maximumCommitteeCount) {
            newOptions.num_witness = numCommitee.toShort()
        } else {
            newOptions.num_witness = maximumCommitteeCount
        }
        newOptions.voting_account = votingAccountId

        val fee = AssetAmount(0, feeAssetId)
        var op = AccountUpdateOperation(fee, myAccountId, null, newOptions, null)

        val fees = getRequiredFee(feeAssetId.toString(), listOf(OperationTuple(op.opTye(),op)))
        if((fees == null)) {
            return null
        }
        if(fees.isEmpty()) {
            return null
        }
        op.fee.amount = fees[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }

    fun callContract(key:String, from:String, contractName:String, method:String, parameters:Any? = null, amount:AssetAmount? = null, feeAsset:String = "GXC"):Any? {
        var myAccount = getAccountByName(from)
        myAccount ?: throw Exception("Bad Parameter ")
        val myAccountId = myAccount.id

        val contract = getContractAccountByName(contractName)
        contract ?: throw Exception("Bad Parameter ")
        val contractId = contract.id

        val data = serializeContractCallArgs(contractName, method, parameters)
        data ?: throw Exception("Bad Parameter ")

        var assets = lookupAssetSymbols(listOf(feeAsset))
        assets ?: throw Exception("Bad Parameter ")

        if(assets.isEmpty()) {
            throw Exception("Bad Parameter ")
        }
        val feeAssetId = assets[0].id
        var fee = AssetAmount(0, feeAssetId)

        var op = CallContractOperation(fee, myAccountId, contractId, method, data, amount)

        val fees = getRequiredFee(feeAssetId.toString(), listOf(OperationTuple(op.opTye(),op)))
        if((fees == null)) {
            return null
        }
        if(fees.isEmpty()) {
            return null
        }
        op.fee.amount = fees[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }

    fun createContract(key:String, from:String, contractName:String, code:String, abi:Abi, vmType:String, vmVersion:String, feeAsset:String = "GXC"):Any? {
        var myAccount = getAccountByName(from)
        myAccount ?: throw Exception("Bad Parameter ")
        val myAccountId = myAccount.id

        if(code.isEmpty()) {
            throw Exception("Bad Parameter ")
        }

        var assets = lookupAssetSymbols(listOf(feeAsset))
        assets ?: throw Exception("Bad Parameter ")

        if(assets.isEmpty()) {
            throw Exception("Bad Parameter ")
        }
        val feeAssetId = assets[0].id
        var fee = AssetAmount(0, feeAssetId)

        var op = CreateContractOperation(fee, contractName, myAccountId, vmType, vmVersion, code, abi)

        val fees = getRequiredFee(feeAssetId.toString(), listOf(OperationTuple(op.opTye(),op)))
        if((fees == null)) {
            return null
        }
        if(fees.isEmpty()) {
            return null
        }
        op.fee.amount = fees[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }

    fun updateContract(key:String, from:String, contractName:String, code:String, abi:Abi, newOwner:String? = null, feeAsset:String = "GXC"):Any? {
        var myAccount = getAccountByName(from)
        myAccount ?: throw Exception("Bad Parameter ")
        val myAccountId = myAccount.id

        if(code.isEmpty()) {
            throw Exception("Bad Parameter ")
        }

        val contract = getContractAccountByName(contractName)
        contract ?: throw Exception("Bad Parameter ")
        val contractId = contract.id

        var newOwnerId:GrapheneId? = null
        if(newOwner != null) {
            val newAccount = getAccountByName(newOwner)
            newAccount ?: throw Exception("Bad Parameter ")
            newOwnerId = newAccount.id
        }
        var assets = lookupAssetSymbols(listOf(feeAsset))
        assets ?: throw Exception("Bad Parameter ")

        if(assets.isEmpty()) {
            throw Exception("Bad Parameter ")
        }
        val feeAssetId = assets[0].id
        var fee = AssetAmount(0, feeAssetId)

        var op = UpdateContractOperation(fee, myAccountId, newOwnerId, contractId, code, abi)

        val fees = getRequiredFee(feeAssetId.toString(), listOf(OperationTuple(op.opTye(),op)))
        if((fees == null)) {
            return null
        }
        if(fees.isEmpty()) {
            return null
        }
        op.fee.amount = fees[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }

    fun getSignedProxyTransferParams(key:String, from:String, to:String, proxy:String, amount:AssetAmount, percentage:Int, memo:String):SignedProxyTransferParams? {
        val fromAccount = getAccountByName(from);
        fromAccount ?:return null

        val fromAccountId = fromAccount.id

        val toAccount = getAccountByName(to)
        toAccount ?:return null

        val toAccountId = toAccount.id

        val proxyAccount = getAccountByName(proxy)
        proxyAccount ?:return null
        val proxyAccountId = proxyAccount.id

        val props = getDynamicGlobalProperties()
        props ?:return null

        val expiration = TransactionUtils.defaultExpiry(props.time, 10)
        val signedProxyTransferParams = SignedProxyTransferParams(fromAccountId, toAccountId, proxyAccountId,
        amount, percentage, memo, expiration)
        signedProxyTransferParams.sign(key)
        return signedProxyTransferParams
    }

    fun proxyTransfer(key:String , proxyMemo:String , requestParams:SignedProxyTransferParams , feeAsset:String = "GXC"):Any? {
        var assets = lookupAssetSymbols(listOf(feeAsset))
        assets ?: throw Exception("Bad Parameter ")

        if(assets.isEmpty()) {
            throw Exception("Bad Parameter ")
        }
        val feeAssetId = assets[0].id
        var fee = AssetAmount(0, feeAssetId)

        var op = ProxyTransferOperation(fee, proxyMemo, requestParams)

        val fees = getRequiredFee(feeAssetId.toString(), listOf(OperationTuple(op.opTye(),op)))
        if((fees == null)) {
            return null
        }
        if(fees.isEmpty()) {
            return null
        }
        op.fee.amount = fees[0].amount
        val ops = listOf(OperationTuple(op.opTye(),op))
        val signedTransaction = sign(listOf(key), ops)
        return broadcastTransaction(signedTransaction!!.tx)
    }


    companion object {
        var Id:Int = 0

        fun register(account:String, activeKey:String, faucet:String, ownerKey:String? = null, memoKey:String? = null):Any? {
            var owner:String? = ownerKey
            if(ownerKey == null) {
                owner = activeKey
            }

            var memo:String? = memoKey
            if(memoKey == null) {
                memo = activeKey
            }
            val registerAccountInfo = RegisterAccountInfo(account, activeKey, owner, memo)
            val registerAccount = RegisterAccount(registerAccountInfo)

            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()

            val chainApi = Api(faucet, okHttpClient).chain

            try {
                val ret = chainApi.register(registerAccount)
                var t = ret.blockingGet()
                if(t.isSuccessful) {
                    return t.body()
                }
            }catch (e: JsonDataException) {
                e.printStackTrace()
            }

            return null;
        }
    }
}