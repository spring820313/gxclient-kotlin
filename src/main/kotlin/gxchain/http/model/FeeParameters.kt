package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable



@JsonClass(generateAdapter = true)
class TransferOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeTransfer.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class LimitOrderCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeLimitOrderCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class LimitOrderCancelOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeLimitOrderCancel.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class CallOrderUpdateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeCallOrderUpdate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class FillOrderOperationFeeParameters(): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeFillOrder.op);
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountCreateOperationFeeParameters(var basic_fee:Long, var premium_fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAccountCreate.op);
        encoder.encodeLong(this.basic_fee)
        encoder.encodeLong(this.premium_fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountUpdateOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAccountUpdate.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountWhitelistOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAccountWhitelist.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountUpgradeOperationFeeParameters(var membership_annual_fee:Long, var membership_lifetime_fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAccountUpgrade.op);
        encoder.encodeLong(this.membership_annual_fee)
        encoder.encodeLong(this.membership_lifetime_fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountTransferOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAccountTransfer.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetCreateOperationFeeParameters(var symbol3:Long, var symbol4:Long, var long_symbol:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetCreate.op);
        encoder.encodeLong(this.symbol3)
        encoder.encodeLong(this.symbol4)
        encoder.encodeLong(this.long_symbol)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetUpdateOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetUpdate.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetUpdateBitassetOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetUpdateBitasset.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssetUpdateFeedProducersOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetUpdateFeedProducers.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetIssueOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetIssue.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetReserveOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetReserve.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetFundFeePoolOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetFundFeePool.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetSettleOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetSettle.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssetGlobalSettleOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetGlobalSettle.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssetPublishFeedOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetPublishFeed.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WitnessCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWitnessCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WitnessUpdateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWitnessUpdate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class ProposalCreateOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeProposalCreate.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class ProposalUpdateOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeProposalUpdate.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class ProposalDeleteOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeProposalDelete.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WithdrawPermissionCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWithdrawPermissionCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WithdrawPermissionUpdateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWithdrawPermissionUpdate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WithdrawPermissionClaimOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWithdrawPermissionClaim.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class WithdrawPermissionDeleteOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWithdrawPermissionDelete.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class CommitteeMemberCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeCommitteeMemberCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class CommitteeMemberUpdateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeCommitteeMemberUpdate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class CommitteeMemberUpdateGlobalParametersOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeCommitteeMemberUpdateGlobalParameters.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class VestingBalanceCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeVestingBalanceCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class VestingBalanceWithdrawOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeVestingBalanceWithdraw.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class WorkerCreateOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeWorkerCreate.op);
        encoder.encodeLong(this.fee)
        return true
    }
}


@JsonClass(generateAdapter = true)
class CustomOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeCustom.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssertOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssert.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class BalanceClaimOperationFeeParameters(): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeBalanceClaim.op);
        return true
    }
}

@JsonClass(generateAdapter = true)
class OverrideTransferOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeOverrideTransfer.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class TransferToBlindOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeTransferToBlind.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class BlindTransferOperationFeeParameters(var fee:Long, var price_per_kbyte:Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeBlindTransfer.op);
        encoder.encodeLong(this.fee)
        encoder.encodeInt(this.price_per_kbyte)
        return true
    }
}


@JsonClass(generateAdapter = true)
class TransferFromBlindOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeTransferFromBlind.op);
        encoder.encodeLong(this.fee)
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssetSettleCancelOperationFeeParameters(): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetSettleCancel.op);
        return true
    }
}


@JsonClass(generateAdapter = true)
class AssetClaimFeesOperationFeeParameters(var fee:Long): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(FeeParametersType.FeeParametersTypeAssetClaimFees.op);
        encoder.encodeLong(this.fee)
        return true
    }
}



class FeeParameters(var type:FeeParametersType, var data:Any): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        var t:Serializable?
        when(this.type) {
            FeeParametersType.FeeParametersTypeTransfer ->
                t = this.data as TransferOperationFeeParameters
            FeeParametersType.FeeParametersTypeLimitOrderCreate ->
                t = this.data as LimitOrderCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeLimitOrderCancel ->
                t = this.data as LimitOrderCancelOperationFeeParameters
             FeeParametersType.FeeParametersTypeCallOrderUpdate ->
                t = this.data as CallOrderUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeFillOrder ->
                t = this.data as FillOrderOperationFeeParameters
             FeeParametersType.FeeParametersTypeAccountCreate ->
                t = this.data as AccountCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeAccountUpdate ->
                t = this.data as AccountUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeAccountWhitelist ->
                t = this.data as AccountWhitelistOperationFeeParameters
            FeeParametersType.FeeParametersTypeAccountUpgrade ->
                t = this.data as AccountUpgradeOperationFeeParameters
             FeeParametersType.FeeParametersTypeAccountTransfer ->
                t = this.data as AccountTransferOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetCreate ->
                t = this.data as AssetCreateOperationFeeParameters
             FeeParametersType.FeeParametersTypeAssetUpdate ->
                t = this.data as AssetUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetUpdateBitasset ->
                t = this.data as AssetUpdateBitassetOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetUpdateFeedProducers ->
                t = this.data as AssetUpdateFeedProducersOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetIssue ->
                t = this.data as AssetIssueOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetReserve ->
                t = this.data as AssetReserveOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetFundFeePool ->
                t = this.data as AssetFundFeePoolOperationFeeParameters
             FeeParametersType.FeeParametersTypeAssetSettle ->
                t = this.data as AssetSettleOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetGlobalSettle ->
                t = this.data as AssetGlobalSettleOperationFeeParameters
             FeeParametersType.FeeParametersTypeAssetPublishFeed ->
                t = this.data as AssetPublishFeedOperationFeeParameters
            FeeParametersType.FeeParametersTypeWitnessCreate ->
                t = this.data as WitnessCreateOperationFeeParameters
             FeeParametersType.FeeParametersTypeWitnessUpdate ->
                t = this.data as WitnessUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeProposalCreate ->
                t = this.data as ProposalCreateOperationFeeParameters
             FeeParametersType.FeeParametersTypeProposalUpdate ->
                t = this.data as ProposalUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeProposalDelete ->
                t = this.data as ProposalDeleteOperationFeeParameters
             FeeParametersType.FeeParametersTypeWithdrawPermissionCreate ->
                t = this.data as WithdrawPermissionCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeWithdrawPermissionUpdate ->
                t = this.data as WithdrawPermissionUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeWithdrawPermissionClaim ->
                t = this.data as WithdrawPermissionClaimOperationFeeParameters
            FeeParametersType.FeeParametersTypeWithdrawPermissionDelete ->
                t = this.data as WithdrawPermissionDeleteOperationFeeParameters
             FeeParametersType.FeeParametersTypeCommitteeMemberCreate ->
                t = this.data as CommitteeMemberCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeCommitteeMemberUpdate ->
                t = this.data as CommitteeMemberUpdateOperationFeeParameters
            FeeParametersType.FeeParametersTypeCommitteeMemberUpdateGlobalParameters ->
                t = this.data as CommitteeMemberUpdateGlobalParametersOperationFeeParameters
            FeeParametersType.FeeParametersTypeVestingBalanceCreate ->
                t = this.data as VestingBalanceCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeVestingBalanceWithdraw ->
                t = this.data as VestingBalanceWithdrawOperationFeeParameters
            FeeParametersType.FeeParametersTypeWorkerCreate ->
                t = this.data as WorkerCreateOperationFeeParameters
            FeeParametersType.FeeParametersTypeCustom ->
                t = this.data as CustomOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssert ->
                t = this.data as AssertOperationFeeParameters
             FeeParametersType.FeeParametersTypeBalanceClaim ->
                t = this.data as BalanceClaimOperationFeeParameters
            FeeParametersType.FeeParametersTypeOverrideTransfer ->
                t = this.data as OverrideTransferOperationFeeParameters
            FeeParametersType.FeeParametersTypeTransferToBlind ->
                t = this.data as TransferToBlindOperationFeeParameters
            FeeParametersType.FeeParametersTypeBlindTransfer ->
                t = this.data as BlindTransferOperationFeeParameters
            FeeParametersType.FeeParametersTypeTransferFromBlind ->
                t = this.data as TransferFromBlindOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetSettleCancel ->
                t = this.data as AssetSettleCancelOperationFeeParameters
            FeeParametersType.FeeParametersTypeAssetClaimFees ->
                t = this.data as AssetClaimFeesOperationFeeParameters
            else -> {
                throw  Exception("type mismatch")
            }
        }
        if(t != null) {
            t.serialize(encoder)
        }
        return true
    }
}