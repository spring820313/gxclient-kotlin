package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable


@JsonClass(generateAdapter = true)
class RefundWorkerInitializer(): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(WorkerInitializerType.WorkerInitializerTypeRefund.op)
        return true
    }
}

@JsonClass(generateAdapter = true)
class VestingBalanceWorkerInitializer(var pay_vesting_period_days: Int): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(WorkerInitializerType.WorkerInitializerTypeRefund.op);
        encoder.encodeShort(this.pay_vesting_period_days.toShort());
        return true
    }
}

@JsonClass(generateAdapter = true)
class BurnWorkerInitializer(): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(WorkerInitializerType.WorkerInitializerTypeBurn.op)
        return true
    }
}

class WorkerInitializer(var type:WorkerInitializerType, var initializer:Any): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        if(this.type == WorkerInitializerType.WorkerInitializerTypeRefund) {
            val p = this.initializer as RefundWorkerInitializer
            p.serialize(encoder)
        } else if(this.type == WorkerInitializerType.WorkerInitializerTypeVestingBalance) {
            val p = this.initializer as VestingBalanceWorkerInitializer
            p.serialize(encoder)
        }else if(this.type == WorkerInitializerType.WorkerInitializerTypeBurn) {
            val p = this.initializer as BurnWorkerInitializer
            p.serialize(encoder)
        }
        return true
    }
}