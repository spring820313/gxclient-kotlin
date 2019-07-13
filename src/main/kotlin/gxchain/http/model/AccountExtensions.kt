package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class NullExtension(): Serializable{
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(AccountCreateExtensionsType.AccountCreateExtensionsNullExt.op)
        return true
    }
}

@JsonClass(generateAdapter = true)
class BuybackOptions(var asset_to_buy:GrapheneId,
                     var asset_to_buy_issuer:GrapheneId,
                     var markets:List<GrapheneId> = listOf()): Serializable{
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(AccountCreateExtensionsType.AccountCreateExtensionsBuyback.op)
        this.asset_to_buy.serialize(encoder)
        this.asset_to_buy_issuer.serialize(encoder)
        encoder.encodeVariableUInt(this.markets.size.toLong())
        for(m in markets) {
            m.serialize(encoder)
        }
        return true
    }
}

@JsonClass(generateAdapter = true)
class AccountCreateExtensions(var null_ext:NullExtension? = null,
                     var owner_special_authority:OwnerSpecialAuthority? = null,
                     var active_special_authority:ActiveSpecialAuthority? = null,
                     var buyback_options:BuybackOptions? = null): Serializable{
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.length())
        if(this.null_ext != null) {
            this.null_ext!!.serialize(encoder)
        }
        if(this.owner_special_authority != null) {
            this.owner_special_authority!!.serialize(encoder)
        }
        if(this.active_special_authority != null) {
            this.active_special_authority!!.serialize(encoder)
        }
        if(this.buyback_options != null) {
            this.buyback_options!!.serialize(encoder)
        }

        return true
    }

    fun length():Long {
        var fields = 0L
        if(this.null_ext != null) fields++
        if(this.owner_special_authority != null) fields++
        if(this.active_special_authority != null) fields++
        if(this.buyback_options != null) fields++
        return fields
    }
}

@JsonClass(generateAdapter = true)
class AccountUpdateExtensions(var null_ext:NullExtension? = null,
                              var owner_special_authority:OwnerSpecialAuthority? = null,
                              var active_special_authority:ActiveSpecialAuthority? = null
                              ): Serializable{
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.length())
        if(this.null_ext != null) {
            this.null_ext!!.serialize(encoder)
        }
        if(this.owner_special_authority != null) {
            this.owner_special_authority!!.serialize(encoder)
        }
        if(this.active_special_authority != null) {
            this.active_special_authority!!.serialize(encoder)
        }

        return true
    }

    fun length():Long {
        var fields = 0L
        if(this.null_ext != null) fields++
        if(this.owner_special_authority != null) fields++
        if(this.active_special_authority != null) fields++
        return fields
    }
}