package gxchain.http.model

import com.squareup.moshi.JsonClass
import com.sun.org.apache.xpath.internal.operations.Bool
import gxchain.encoder.Encoder
import gxchain.encoder.Serializable
import java.util.*

@JsonClass(generateAdapter = true)
class TypeDef(
    var new_type_name:String,
    var type:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeString(this.new_type_name)
        encoder.encodeString(this.type)
        return true
    }
}

@JsonClass(generateAdapter = true)
class ErrorMessage(
    var error_code:Long,
    var error_msg:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeLong(this.error_code)
        encoder.encodeString(this.error_msg)
        return true
    }
}

@JsonClass(generateAdapter = true)
class Field(
    var name:String,
    var type:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeString(this.name)
        encoder.encodeString(this.type)
        return true
    }
}

@JsonClass(generateAdapter = true)
class Struct(
    var name:String,
    var base:String,
    var fields:List<Field> = listOf()): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeString(this.name)
        encoder.encodeString(this.base)
        encoder.encodeVariableUInt(fields.size.toLong())
        for(f in this.fields) {
            f.serialize(encoder)
        }
        return true;
    }
}

@JsonClass(generateAdapter = true)
class Table(
    var name:String,
    var index_type:String,
    var key_names:List<String> = listOf(),
    var key_types:List<String> = listOf(),
    var type:String): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeName(this.name)
        encoder.encodeString(this.index_type)
        encoder.encodeVariableUInt(key_names.size.toLong())
        for(n in this.key_names) {
            encoder.encodeString(n)
        }
        encoder.encodeVariableUInt(key_types.size.toLong())
        for(t in this.key_types) {
            encoder.encodeString(t)
        }
        encoder.encodeString(this.type)
        return true;
    }
}

@JsonClass(generateAdapter = true)
class Action(
    var name:String,
    var type:String,
    var payable:Boolean): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeName(this.name)
        encoder.encodeString(this.type)
        encoder.encodeBool(this.payable)
        return true
    }
}

@JsonClass(generateAdapter = true)
data class Abi(
    var version:String,
    var types:List<TypeDef> = listOf(),
    var structs:List<Struct> = listOf(),
    var actions:List<Action> = listOf(),
    var tables:List<Table> = listOf(),
    var error_messages:List<ErrorMessage> = listOf(),
    var abi_extensions:List<Any> = listOf()): Serializable {
    override fun serialize(encoder: Encoder):Boolean {
        encoder.encodeString(this.version);
        encoder.encodeVariableUInt(types.size.toLong());
        for(t in this.types) {
            t.serialize(encoder);
        }

        encoder.encodeVariableUInt(structs.size.toLong());
        for(s in this.structs) {
            s.serialize(encoder);
        }

        encoder.encodeVariableUInt(actions.size.toLong());
        for(a in this.actions) {
            a.serialize(encoder);
        }

        encoder.encodeVariableUInt(tables.size.toLong());
        for(ta in this.tables) {
            ta.serialize(encoder);
        }

        encoder.encodeVariableUInt(error_messages.size.toLong());
        for(e in this.error_messages) {
            e.serialize(encoder);
        }

        encoder.encodeVariableUInt(0L);
        return true;
    }
}

@JsonClass(generateAdapter = true)
data class ContractAccountProperties (
    var id:GrapheneId,
    var membership_expiration_date:String,
    var registrar:String,
    var referrer:String,
    var lifetime_referrer:String,
    var name:String,
    var statistics:String,
    var network_fee_percentage:Long,
    var lifetime_referrer_fee_percentage:Long,
    var referrer_rewards_percentage:Long,
    var whitelisting_accounts:List<GrapheneId> = listOf(),
    var blacklisting_accounts:List<GrapheneId> = listOf(),
    var whitelisted_accounts:List<GrapheneId> = listOf(),
    var blacklisted_accounts:List<GrapheneId> = listOf(),
    var abi:Abi,
    var vm_type:String,
    var vm_version:String,
    var code:String,
    var code_version:String
    )