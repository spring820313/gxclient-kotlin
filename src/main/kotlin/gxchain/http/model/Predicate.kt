package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.hex.DefaultHexWriter
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class AccountNameEqLitPredicate(var account_id:GrapheneId, var name:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(PredicateType.PredicateAccountNameEqLit.op);
        this.account_id.serialize(encoder);
        encoder.encodeString(this.name);
        return true
    }
}

@JsonClass(generateAdapter = true)
class AssetSymbolEqLitPredicate(var asset_id:GrapheneId, var symbol:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(PredicateType.PredicateAssetSymbolEqLit.op);
        this.asset_id.serialize(encoder);
        encoder.encodeString(this.symbol);
        return true
    }
}

@JsonClass(generateAdapter = true)
class BlockIdPredicate(var id:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(PredicateType.PredicateBlockId.op);
        val idBytes = DefaultHexWriter().hexToBytes(this.id)
        encoder.encodeVariableUInt(idBytes.size.toLong())
        encoder.encodeBytes(idBytes)
        return true
    }
}

class Predicate(var type:PredicateType, var pre:Any):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        if(this.type == PredicateType.PredicateAccountNameEqLit) {
            val p = this.pre as AccountNameEqLitPredicate
            p.serialize(encoder)
        } else if(this.type == PredicateType.PredicateAssetSymbolEqLit) {
            val p = this.pre as AssetSymbolEqLitPredicate
            p.serialize(encoder)
        } else if(this.type == PredicateType.PredicateBlockId) {
            val p = this.pre as BlockIdPredicate
            p.serialize(encoder)
        }
        return true
    }
}