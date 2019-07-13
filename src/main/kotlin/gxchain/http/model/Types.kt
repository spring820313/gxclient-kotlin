package gxchain.http.model

enum class OpType(var op: Byte) {
    TransferOpType(0),
    LimitOrderCreateOpType(1),
    LimitOrderCancelOpType(2),
    CallOrderUpdateOpType(3),
    FillOrderOpType(4),
    AccountCreateOpType(5),
    AccountUpdateOpType(6),
    AccountWhitelistOpType(7),
    AccountUpgradeOpType(8),
    AccountTransferOpType(9),
    AssetCreateOpType(10),
    AssetUpdateOpType(11),
    AssetUpdateBitassetOpType(12),
    AssetUpdateFeedProducersOpType(13),
    AssetIssueOpType(14),
    AssetReserveOpType(15),
    AssetFundFeePoolOpType(16),
    AssetSettleOpType(17),
    AssetGlobalSettleOpType(18),
    AssetPublishFeedOpType(19),
    WitnessCreateOpType(20),
    WitnessUpdateOpType(21),
    ProposalCreateOpType(22),
    ProposalUpdateOpType(23),
    ProposalDeleteOpType(24),
    WithdrawPermissionCreateOpType(25),
    WithdrawPermissionUpdateOpType(26),
    WithdrawPermissionClaimOpType(27),
    WithdrawPermissionDeleteOpType(28),
    CommitteeMemberCreateOpType(29),
    CommitteeMemberUpdateOpType(30),
    CommitteeMemberUpdateGlobalParametersOpType(31),
    VestingBalanceCreateOpType(32),
    VestingBalanceWithdrawOpType(33),
    WorkerCreateOpType(34),
    CustomOpType(35),
    AssertOpType(36),
    BalanceClaimOpType(37),
    OverrideTransferOpType(38),
    TransferToBlindOpType(39),
    BlindTransferOpType(40),
    TransferFromBlindOpType(41),
    AssetSettleCancelOpType(42),
    AssetClaimFeesOpType(43),
    FbaDistributeOperationOpType(44),
    AccountUpgradeMerchantOpType(45),
    AccountUpgradeDatasourceOpType(46),
    StaleDataMarketCategoryCreateOpType(47),
    StaleDataMarketCategoryUpdateOpType(48),
    StaleFreeDataProductCreateOpType(49),
    StaleFreeDataProductUpdateOpType(50),
    StaleLeagueDataProductCreateOpType(51),
    StaleLeagueDataProductUpdateOpType(52),
    StaleLeagueCreateOpType(53),
    StaleLeagueUpdateOpType(54),
    DataTransactionCreateOpType(55),
    DataTransactionUpdateOpType(56),
    DataTransactionPayOpType(57),
    AccountUpgradeDataTransactionMemberOpType(58),
    DataTransactionDatasourceUploadOpType(59),
    DataTransactionDatasourceValidateErrorOpType(60),
    DataMarketCategoryCreateOpType(61),
    DataMarketCategoryUpdateOpType(62),
    FreeDataProductCreateOpType(63),
    FreeDataProductUpdateOpType(64),
    LeagueDataProductCreateOpType(65),
    LeagueDataProductUpdateOpType(66),
    LeagueCreateOpType(67),
    LeagueUpdateOpType(68),
    DatasourceCopyrightClearOpType(69),
    DataTransactionComplainOpType(70),
    BalanceLockOpType(71),
    BalanceUnlockOpType(72),
    ProxyTransferOpType(73),
    CreateContractOpType(74),
    CallContractOpType(75),
    UpdateContractOpType(76),
    TrustNodePledgeWithdrawOpType(77),
    InlineTransferOpType(78),
    InterContractCallOpType(79)
}

enum class SpecialAuthorityType(var op: Byte) {
    SpecialAuthorityTypeNoSpecial(0),
    SpecialAuthorityTypeTopHolders(1);
    companion object {
        fun fromByte(x:Byte): SpecialAuthorityType {
            when(x) {
                0.toByte() -> return SpecialAuthorityTypeNoSpecial
                1.toByte() -> return SpecialAuthorityTypeTopHolders
                else -> return SpecialAuthorityTypeNoSpecial
            }
        }
    }
}

enum class AccountCreateExtensionsType(var op: Byte) {
    AccountCreateExtensionsNullExt(0),
    AccountCreateExtensionsOwnerSpecial(1),
    AccountCreateExtensionsActiveSpecial(2),
    AccountCreateExtensionsBuyback(3);

    companion object {
        fun fromByte(x:Byte): AccountCreateExtensionsType {
            when(x) {
                0.toByte() -> return AccountCreateExtensionsNullExt
                1.toByte() -> return AccountCreateExtensionsOwnerSpecial
                2.toByte() -> return AccountCreateExtensionsActiveSpecial
                3.toByte() -> return AccountCreateExtensionsBuyback
                else -> return AccountCreateExtensionsNullExt
            }
        }
    }
}

enum class PredicateType(var op: Byte) {
    PredicateAccountNameEqLit(0),
    PredicateAssetSymbolEqLit(1),
    PredicateBlockId(2)
}

enum class VestingPolicyType(var op: Byte) {
    VestingPolicyTypeLinear(0),
    VestingPolicyTypeCCD(1)
}

enum class WorkerInitializerType(var op: Byte) {
    WorkerInitializerTypeRefund(0),
    WorkerInitializerTypeVestingBalance(1),
    WorkerInitializerTypeBurn(2)
}

enum class FeeParametersType(var op: Byte) {
    FeeParametersTypeTransfer(0),
    FeeParametersTypeLimitOrderCreate(1),
    FeeParametersTypeLimitOrderCancel(2),
    FeeParametersTypeCallOrderUpdate(3),
    FeeParametersTypeFillOrder(4),
    FeeParametersTypeAccountCreate(5),
    FeeParametersTypeAccountUpdate(6),
    FeeParametersTypeAccountWhitelist(7),
    FeeParametersTypeAccountUpgrade(8),
    FeeParametersTypeAccountTransfer(9),
    FeeParametersTypeAssetCreate(10),
    FeeParametersTypeAssetUpdate(11),
    FeeParametersTypeAssetUpdateBitasset(12),
    FeeParametersTypeAssetUpdateFeedProducers(13),
    FeeParametersTypeAssetIssue(14),
    FeeParametersTypeAssetReserve(15),
    FeeParametersTypeAssetFundFeePool(16),
    FeeParametersTypeAssetSettle(17),
    FeeParametersTypeAssetGlobalSettle(18),
    FeeParametersTypeAssetPublishFeed(19),
    FeeParametersTypeWitnessCreate(20),
    FeeParametersTypeWitnessUpdate(21),
    FeeParametersTypeProposalCreate(22),
    FeeParametersTypeProposalUpdate(23),
    FeeParametersTypeProposalDelete(24),
    FeeParametersTypeWithdrawPermissionCreate(25),
    FeeParametersTypeWithdrawPermissionUpdate(26),
    FeeParametersTypeWithdrawPermissionClaim(27),
    FeeParametersTypeWithdrawPermissionDelete(28),
    FeeParametersTypeCommitteeMemberCreate(29),
    FeeParametersTypeCommitteeMemberUpdate(30),
    FeeParametersTypeCommitteeMemberUpdateGlobalParameters(31),
    FeeParametersTypeVestingBalanceCreate(32),
    FeeParametersTypeVestingBalanceWithdraw(33),
    FeeParametersTypeWorkerCreate(34),
    FeeParametersTypeCustom(35),
    FeeParametersTypeAssert(36),
    FeeParametersTypeBalanceClaim(37),
    FeeParametersTypeOverrideTransfer(38),
    FeeParametersTypeTransferToBlind(39),
    FeeParametersTypeBlindTransfer(40),
    FeeParametersTypeTransferFromBlind(41),
    FeeParametersTypeAssetSettleCancel(42),
    FeeParametersTypeAssetClaimFees(43)
}