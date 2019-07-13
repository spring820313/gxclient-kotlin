package gxchain.http.model

import com.squareup.moshi.JsonClass
import gxchain.core.crypto.GxcPublicKey
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable

@JsonClass(generateAdapter = true)
class Authority(var weight_threshold:Int,
                var account_auths:AccountAuthsMap,
                var key_auths:KeyAuthsMap,
                var address_auths:AddressAuthsMap,
                var extensions:List<Any> = listOf()
                ): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeInt(this.weight_threshold)
        this.account_auths.serialize(encoder)
        this.key_auths.serialize(encoder)
        this.address_auths.serialize(encoder)
        encoder.encodeVariableUInt(0L)
        return true
    }
}

class KeyAuthsMap(var auth:Map<GxcPublicKey, Short> = mutableMapOf()):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.auth.size.toLong())
        var keys = this.auth.keys.toList()
        keys.sortedBy { Address(it).toString() }

        for(key in keys) {
            val p = key.toString()
            encoder.encodePubKey(p)
            encoder.encodeShort(auth[key]!!)
        }
        return true
    }
}

class AddressAuthsMap(var auth:Map<Address, Short> = mutableMapOf()):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.auth.size.toLong())
        var keys = this.auth.keys.toList()

        for(key in keys) {
            key.serialize(encoder)
            encoder.encodeShort(auth[key]!!)
        }
        return true
    }
}

class AccountAuthsMap(var auth:Map<GrapheneId, Short> = mutableMapOf()):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeVariableUInt(this.auth.size.toLong())
        var keys = this.auth.keys.toList()

        for(key in keys) {
            key.serialize(encoder)
            encoder.encodeShort(auth[key]!!)
        }
        return true
    }
}

@JsonClass(generateAdapter = true)
class NoSpecialAuthority:Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        return true
    }
}

@JsonClass(generateAdapter = true)
class TopHoldersSpecialAuthority(var asset:GrapheneId, var num_top_holders:Byte):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        this.asset.serialize(encoder)
        encoder.encodeByte(this.num_top_holders)
        return true
    }
}

class SpecialAuth(var type:SpecialAuthorityType, var auth:Any):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(type.op)
        if(this.type == SpecialAuthorityType.SpecialAuthorityTypeNoSpecial) {
            val noSpecialAuthority = this.auth as NoSpecialAuthority
            noSpecialAuthority.serialize(encoder)
        } else if(this.type == SpecialAuthorityType.SpecialAuthorityTypeTopHolders) {
            val topHoldersSpecialAuthority = this.auth as TopHoldersSpecialAuthority
            topHoldersSpecialAuthority.serialize(encoder)
        }
        return true
    }
}


class OwnerSpecialAuthority(var specialAuth:SpecialAuth):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(AccountCreateExtensionsType.AccountCreateExtensionsOwnerSpecial.op)
        this.specialAuth.serialize(encoder)
        return true
    }
}


class ActiveSpecialAuthority(var specialAuth:SpecialAuth):Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeByte(AccountCreateExtensionsType.AccountCreateExtensionsActiveSpecial.op)
        this.specialAuth.serialize(encoder)
        return true
    }
}