package gxchain.http.utils

import com.squareup.moshi.*
import gxchain.core.operation.*
import gxchain.http.model.*
import java.io.IOException

class OperationTupleAdapter : JsonAdapter<OperationTuple>() {
    private val moshi = Moshi.Builder()
        .add(DateAdapter())
        .add(GrapheneIdAdapter())
        .add(VoteIDAdapter())
        .add(GxcPublicKeyAdapter()).build()
    companion object {
        val FACTORY = Factory { type, _, _ ->
            return@Factory if (type === OperationTuple::class.java) {
                OperationTupleAdapter()
            } else {
                null
            }
        }
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): OperationTuple? {
        var lst = mutableListOf<Any>()
        reader.beginArray()

        var t = OpType.TransferOpType.op
        if(reader.hasNext()) {
            val type = reader.nextInt().toByte()
            lst.add(type)
            t = type
        }
        var o:Operation? = null
        if(reader.hasNext()) {

            when(t) {
                OpType.AccountCreateOpType.op ->  o = moshi.adapter(AccountCreateOperation::class.java).fromJson(reader)
                OpType.AccountTransferOpType.op ->  o = moshi.adapter(AccountTransferOperation::class.java).fromJson(reader)
                OpType.AccountUpgradeOpType.op ->  o = moshi.adapter(AccountUpgradeOperation::class.java).fromJson(reader)
                OpType.AccountWhitelistOpType.op ->  o = moshi.adapter(AccountWhitelistOperation::class.java).fromJson(reader)
                OpType.AssertOpType.op ->  o = moshi.adapter(AssertOperation::class.java).fromJson(reader)
                OpType.AssetClaimFeesOpType.op ->  o = moshi.adapter(AssetClaimFeesOperation::class.java).fromJson(reader)
                OpType.AssetCreateOpType.op ->  o = moshi.adapter(AssetCreateOperation::class.java).fromJson(reader)
                OpType.AssetFundFeePoolOpType.op ->  o = moshi.adapter(AssetFundFeePoolOperation::class.java).fromJson(reader)
                OpType.AssetGlobalSettleOpType.op ->  o = moshi.adapter(AssetGlobalSettleOperation::class.java).fromJson(reader)
                OpType.AssetIssueOpType.op ->  o = moshi.adapter(AssetIssueOperation::class.java).fromJson(reader)
                OpType.AssetPublishFeedOpType.op ->  o = moshi.adapter(AssetPublishFeedOperation::class.java).fromJson(reader)
                OpType.AssetReserveOpType.op ->  o = moshi.adapter(AssetReserveOperation::class.java).fromJson(reader)
                OpType.AssetSettleOpType.op ->  o = moshi.adapter(AssetSettleOperation::class.java).fromJson(reader)
                OpType.AssetSettleCancelOpType.op ->  o = moshi.adapter(AssetSettleCancelOperation::class.java).fromJson(reader)
                OpType.AssetUpdateOpType.op ->  o = moshi.adapter(AssetUpdateOperation::class.java).fromJson(reader)
                OpType.AssetUpdateBitassetOpType.op ->  o = moshi.adapter(AssetUpdateBitassetOperation::class.java).fromJson(reader)
                OpType.AssetUpdateFeedProducersOpType.op ->  o = moshi.adapter(AssetUpdateFeedProducersOperation::class.java).fromJson(reader)
                OpType.BalanceClaimOpType.op ->  o = moshi.adapter(BalanceClaimOperation::class.java).fromJson(reader)
                OpType.BalanceLockOpType.op ->  o = moshi.adapter(BalanceLockOperation::class.java).fromJson(reader)
                OpType.BalanceUnlockOpType.op ->  o = moshi.adapter(BalanceUnlockOperation::class.java).fromJson(reader)
                OpType.BlindTransferOpType.op ->  o = moshi.adapter(BlindTransferOperation::class.java).fromJson(reader)
                OpType.CallOrderUpdateOpType.op ->  o = moshi.adapter(CallOrderUpdateOperation::class.java).fromJson(reader)
                OpType.CommitteeMemberCreateOpType.op ->  o = moshi.adapter(CommitteeMemberCreateOperation::class.java).fromJson(reader)
                OpType.CommitteeMemberUpdateOpType.op ->  o = moshi.adapter(CommitteeMemberUpdateOperation::class.java).fromJson(reader)
                OpType.CommitteeMemberUpdateGlobalParametersOpType.op ->  o = moshi.adapter(CommitteeMemberUpdateGlobalParametersOperation::class.java).fromJson(reader)
                OpType.CustomOpType.op ->  o = moshi.adapter(CustomOperation::class.java).fromJson(reader)
                OpType.DataTransactionCreateOpType.op ->  o = moshi.adapter(DataTransactionCreateOperation::class.java).fromJson(reader)
                OpType.DataTransactionDatasourceUploadOpType.op ->  o = moshi.adapter(DataTransactionDatasourceUploadOperation::class.java).fromJson(reader)
                OpType.DataTransactionDatasourceValidateErrorOpType.op ->  o = moshi.adapter(DataTransactionDatasourceValidateErrorOperation::class.java).fromJson(reader)
                OpType.DataTransactionPayOpType.op ->  o = moshi.adapter(DataTransactionPayOperation::class.java).fromJson(reader)
                OpType.DataTransactionUpdateOpType.op ->  o = moshi.adapter(DataTransactionUpdateOperation::class.java).fromJson(reader)
                OpType.FbaDistributeOperationOpType.op ->  o = moshi.adapter(FbaDistributeOperation::class.java).fromJson(reader)
                OpType.FillOrderOpType.op ->  o = moshi.adapter(FillOrderOperation::class.java).fromJson(reader)
                OpType.InlineTransferOpType.op ->  o = moshi.adapter(InlineTransferOperation::class.java).fromJson(reader)
                OpType.InterContractCallOpType.op ->  o = moshi.adapter(InterContractCallOperation::class.java).fromJson(reader)
                OpType.LimitOrderCancelOpType.op ->  o = moshi.adapter(LimitOrderCancelOperation::class.java).fromJson(reader)
                OpType.LimitOrderCreateOpType.op ->  o = moshi.adapter(LimitOrderCreateOperation::class.java).fromJson(reader)
                OpType.OverrideTransferOpType.op ->  o = moshi.adapter(OverrideTransferOperation::class.java).fromJson(reader)
                OpType.ProposalCreateOpType.op ->  o = moshi.adapter(ProposalCreateOperation::class.java).fromJson(reader)
                OpType.ProposalDeleteOpType.op ->  o = moshi.adapter(ProposalDeleteOperation::class.java).fromJson(reader)
                OpType.ProposalUpdateOpType.op ->  o = moshi.adapter(ProposalUpdateOperation::class.java).fromJson(reader)
                OpType.TransferFromBlindOpType.op ->  o = moshi.adapter(TransferFromBlindOperation::class.java).fromJson(reader)
                OpType.TransferToBlindOpType.op ->  o = moshi.adapter(TransferToBlindOperation::class.java).fromJson(reader)
                OpType.TrustNodePledgeWithdrawOpType.op ->  o = moshi.adapter(TrustNodePledgeWithdrawOperation::class.java).fromJson(reader)
                OpType.VestingBalanceCreateOpType.op ->  o = moshi.adapter(VestingBalanceCreateOperation::class.java).fromJson(reader)
                OpType.VestingBalanceWithdrawOpType.op ->  o = moshi.adapter(VestingBalanceWithdrawOperation::class.java).fromJson(reader)
                OpType.WithdrawPermissionClaimOpType.op ->  o = moshi.adapter(WithdrawPermissionClaimOperation::class.java).fromJson(reader)
                OpType.WithdrawPermissionCreateOpType.op ->  o = moshi.adapter(WithdrawPermissionCreateOperation::class.java).fromJson(reader)
                OpType.WithdrawPermissionDeleteOpType.op ->  o = moshi.adapter(WithdrawPermissionDeleteOperation::class.java).fromJson(reader)
                OpType.WithdrawPermissionUpdateOpType.op ->  o = moshi.adapter(WithdrawPermissionUpdateOperation::class.java).fromJson(reader)
                OpType.WitnessCreateOpType.op ->  o = moshi.adapter(WitnessCreateOperation::class.java).fromJson(reader)
                OpType.WitnessUpdateOpType.op ->  o = moshi.adapter(WitnessUpdateOperation::class.java).fromJson(reader)
                OpType.WorkerCreateOpType.op ->  o = moshi.adapter(WorkerCreateOperation::class.java).fromJson(reader)
                OpType.TransferOpType.op ->  o = moshi.adapter(TransferOperation::class.java).fromJson(reader)
                OpType.AccountUpdateOpType.op ->  o = moshi.adapter(AccountUpdateOperation::class.java).fromJson(reader)
                OpType.CallContractOpType.op ->  o = moshi.adapter(CallContractOperation::class.java).fromJson(reader)
                OpType.CreateContractOpType.op ->  o = moshi.adapter(CreateContractOperation::class.java).fromJson(reader)
                OpType.UpdateContractOpType.op ->  o = moshi.adapter(UpdateContractOperation::class.java).fromJson(reader)
                OpType.ProxyTransferOpType.op ->  o = moshi.adapter(ProxyTransferOperation::class.java).fromJson(reader)
                else -> {
                    throw  Exception("type mismatch")
                }
            }
            if(o != null) {
                lst.add(o as Any)
            }

        }
        reader.endArray()
        o ?: return null
        return OperationTuple(t, o)
    }

    @Throws(IOException::class)
    override fun toJson(
        writer: JsonWriter,
        value: OperationTuple?
    ) {
        writer.beginArray()
        writer.value(value?.type)

        when(value?.type) {
            OpType.AccountCreateOpType.op -> moshi.adapter(AccountCreateOperation::class.java).toJson(writer, value.op as AccountCreateOperation)
            OpType.AccountTransferOpType.op -> moshi.adapter(AccountTransferOperation::class.java).toJson(writer, value.op as AccountTransferOperation)
            OpType.AccountUpgradeOpType.op -> moshi.adapter(AccountUpgradeOperation::class.java).toJson(writer, value.op as AccountUpgradeOperation)
            OpType.AccountWhitelistOpType.op -> moshi.adapter(AccountWhitelistOperation::class.java).toJson(writer, value.op as AccountWhitelistOperation)
            OpType.AssertOpType.op -> moshi.adapter(AssertOperation::class.java).toJson(writer, value.op as AssertOperation)
            OpType.AssetClaimFeesOpType.op -> moshi.adapter(AssetClaimFeesOperation::class.java).toJson(writer, value.op as AssetClaimFeesOperation)
            OpType.AssetCreateOpType.op -> moshi.adapter(AssetCreateOperation::class.java).toJson(writer, value.op as AssetCreateOperation)
            OpType.AssetFundFeePoolOpType.op -> moshi.adapter(AssetFundFeePoolOperation::class.java).toJson(writer, value.op as AssetFundFeePoolOperation)
            OpType.AssetGlobalSettleOpType.op -> moshi.adapter(AssetGlobalSettleOperation::class.java).toJson(writer, value.op as AssetGlobalSettleOperation)
            OpType.AssetIssueOpType.op -> moshi.adapter(AssetIssueOperation::class.java).toJson(writer, value.op as AssetIssueOperation)
            OpType.AssetPublishFeedOpType.op -> moshi.adapter(AssetPublishFeedOperation::class.java).toJson(writer, value.op as AssetPublishFeedOperation)
            OpType.AssetReserveOpType.op -> moshi.adapter(AssetReserveOperation::class.java).toJson(writer, value.op as AssetReserveOperation)
            OpType.AssetSettleOpType.op -> moshi.adapter(AssetSettleOperation::class.java).toJson(writer, value.op as AssetSettleOperation)
            OpType.AssetSettleCancelOpType.op -> moshi.adapter(AssetSettleCancelOperation::class.java).toJson(writer, value.op as AssetSettleCancelOperation)
            OpType.AssetUpdateOpType.op -> moshi.adapter(AssetUpdateOperation::class.java).toJson(writer, value.op as AssetUpdateOperation)
            OpType.AssetUpdateBitassetOpType.op -> moshi.adapter(AssetUpdateBitassetOperation::class.java).toJson(writer, value.op as AssetUpdateBitassetOperation)
            OpType.AssetUpdateFeedProducersOpType.op -> moshi.adapter(AssetUpdateFeedProducersOperation::class.java).toJson(writer, value.op as AssetUpdateFeedProducersOperation)
            OpType.BalanceClaimOpType.op -> moshi.adapter(BalanceClaimOperation::class.java).toJson(writer, value.op as BalanceClaimOperation)
            OpType.BalanceLockOpType.op -> moshi.adapter(BalanceLockOperation::class.java).toJson(writer, value.op as BalanceLockOperation)
            OpType.BalanceUnlockOpType.op -> moshi.adapter(BalanceUnlockOperation::class.java).toJson(writer, value.op as BalanceUnlockOperation)
            OpType.BlindTransferOpType.op -> moshi.adapter(BlindTransferOperation::class.java).toJson(writer, value.op as BlindTransferOperation)
            OpType.CallOrderUpdateOpType.op -> moshi.adapter(CallOrderUpdateOperation::class.java).toJson(writer, value.op as CallOrderUpdateOperation)
            OpType.CommitteeMemberCreateOpType.op -> moshi.adapter(CommitteeMemberCreateOperation::class.java).toJson(writer, value.op as CommitteeMemberCreateOperation)
            OpType.CommitteeMemberUpdateOpType.op -> moshi.adapter(CommitteeMemberUpdateOperation::class.java).toJson(writer, value.op as CommitteeMemberUpdateOperation)
            OpType.CommitteeMemberUpdateGlobalParametersOpType.op -> moshi.adapter(CommitteeMemberUpdateGlobalParametersOperation::class.java).toJson(writer, value.op as CommitteeMemberUpdateGlobalParametersOperation)
            OpType.CustomOpType.op -> moshi.adapter(CustomOperation::class.java).toJson(writer, value.op as CustomOperation)
            OpType.DataTransactionCreateOpType.op -> moshi.adapter(DataTransactionCreateOperation::class.java).toJson(writer, value.op as DataTransactionCreateOperation)
            OpType.DataTransactionDatasourceUploadOpType.op -> moshi.adapter(DataTransactionDatasourceUploadOperation::class.java).toJson(writer, value.op as DataTransactionDatasourceUploadOperation)
            OpType.DataTransactionDatasourceValidateErrorOpType.op -> moshi.adapter(DataTransactionDatasourceValidateErrorOperation::class.java).toJson(writer, value.op as DataTransactionDatasourceValidateErrorOperation)
            OpType.DataTransactionPayOpType.op -> moshi.adapter(DataTransactionPayOperation::class.java).toJson(writer, value.op as DataTransactionPayOperation)
            OpType.DataTransactionUpdateOpType.op -> moshi.adapter(DataTransactionUpdateOperation::class.java).toJson(writer, value.op as DataTransactionUpdateOperation)
            OpType.FbaDistributeOperationOpType.op -> moshi.adapter(FbaDistributeOperation::class.java).toJson(writer, value.op as FbaDistributeOperation)
            OpType.FillOrderOpType.op -> moshi.adapter(FillOrderOperation::class.java).toJson(writer, value.op as FillOrderOperation)
            OpType.InlineTransferOpType.op -> moshi.adapter(InlineTransferOperation::class.java).toJson(writer, value.op as InlineTransferOperation)
            OpType.InterContractCallOpType.op -> moshi.adapter(InterContractCallOperation::class.java).toJson(writer, value.op as InterContractCallOperation)
            OpType.LimitOrderCancelOpType.op -> moshi.adapter(LimitOrderCancelOperation::class.java).toJson(writer, value.op as LimitOrderCancelOperation)
            OpType.LimitOrderCreateOpType.op -> moshi.adapter(LimitOrderCreateOperation::class.java).toJson(writer, value.op as LimitOrderCreateOperation)
            OpType.OverrideTransferOpType.op -> moshi.adapter(OverrideTransferOperation::class.java).toJson(writer, value.op as OverrideTransferOperation)
            OpType.ProposalCreateOpType.op -> moshi.adapter(ProposalCreateOperation::class.java).toJson(writer, value.op as ProposalCreateOperation)
            OpType.ProposalDeleteOpType.op -> moshi.adapter(ProposalDeleteOperation::class.java).toJson(writer, value.op as ProposalDeleteOperation)
            OpType.ProposalUpdateOpType.op -> moshi.adapter(ProposalUpdateOperation::class.java).toJson(writer, value.op as ProposalUpdateOperation)
            OpType.TransferFromBlindOpType.op -> moshi.adapter(TransferFromBlindOperation::class.java).toJson(writer, value.op as TransferFromBlindOperation)
            OpType.TransferToBlindOpType.op -> moshi.adapter(TransferToBlindOperation::class.java).toJson(writer, value.op as TransferToBlindOperation)
            OpType.TrustNodePledgeWithdrawOpType.op -> moshi.adapter(TrustNodePledgeWithdrawOperation::class.java).toJson(writer, value.op as TrustNodePledgeWithdrawOperation)
            OpType.VestingBalanceCreateOpType.op -> moshi.adapter(VestingBalanceCreateOperation::class.java).toJson(writer, value.op as VestingBalanceCreateOperation)
            OpType.VestingBalanceWithdrawOpType.op -> moshi.adapter(VestingBalanceWithdrawOperation::class.java).toJson(writer, value.op as VestingBalanceWithdrawOperation)
            OpType.WithdrawPermissionClaimOpType.op -> moshi.adapter(WithdrawPermissionClaimOperation::class.java).toJson(writer, value.op as WithdrawPermissionClaimOperation)
            OpType.WithdrawPermissionCreateOpType.op -> moshi.adapter(WithdrawPermissionCreateOperation::class.java).toJson(writer, value.op as WithdrawPermissionCreateOperation)
            OpType.WithdrawPermissionDeleteOpType.op -> moshi.adapter(WithdrawPermissionDeleteOperation::class.java).toJson(writer, value.op as WithdrawPermissionDeleteOperation)
            OpType.WithdrawPermissionUpdateOpType.op -> moshi.adapter(WithdrawPermissionUpdateOperation::class.java).toJson(writer, value.op as WithdrawPermissionUpdateOperation)
            OpType.WitnessCreateOpType.op -> moshi.adapter(WitnessCreateOperation::class.java).toJson(writer, value.op as WitnessCreateOperation)
            OpType.WitnessUpdateOpType.op -> moshi.adapter(WitnessUpdateOperation::class.java).toJson(writer, value.op as WitnessUpdateOperation)
            OpType.WorkerCreateOpType.op -> moshi.adapter(WorkerCreateOperation::class.java).toJson(writer, value.op as WorkerCreateOperation)
            OpType.TransferOpType.op -> moshi.adapter(TransferOperation::class.java).toJson(writer, value.op as TransferOperation)
            OpType.AccountUpdateOpType.op -> moshi.adapter(AccountUpdateOperation::class.java).toJson(writer, value.op as AccountUpdateOperation)
            OpType.CallContractOpType.op -> moshi.adapter(CallContractOperation::class.java).toJson(writer, value.op as CallContractOperation)
            OpType.CreateContractOpType.op -> moshi.adapter(CreateContractOperation::class.java).toJson(writer, value.op as CreateContractOperation)
            OpType.UpdateContractOpType.op -> moshi.adapter(UpdateContractOperation::class.java).toJson(writer, value.op as UpdateContractOperation)
            OpType.ProxyTransferOpType.op -> moshi.adapter(ProxyTransferOperation::class.java).toJson(writer, value.op as ProxyTransferOperation)
            else -> {
                throw  Exception("type mismatch")
            }
        }

        writer.endArray()
    }

    override fun toString(): String {
        return "JsonAdapter(OperationTuple)"
    }
}