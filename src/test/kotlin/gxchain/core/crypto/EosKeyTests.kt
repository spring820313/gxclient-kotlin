package gxchain.core.crypto

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class EosKeyTests {

    @Test
    fun testPublicKeyIsResolvedFromPrivateKey() {
        val privateKey = GxcPrivateKey("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")
        print(privateKey.publicKey.toString())
        assertEquals("EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV", privateKey.publicKey.toString())
    }

    @Test
    fun testGenerateEosPrivateKey() {
        val privateKey = GxcPrivateKey()
        val publicKey = privateKey.publicKey
        assertNotNull(privateKey.toString())
        assertNotNull(publicKey.toString())
    }

    @Test
    fun testCreateEosPrivateKeyFromBytes() {
        val existingPrivateKey = GxcPrivateKey("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")
        val bytes = existingPrivateKey.bytes
        val privateKey = GxcPrivateKey(bytes)
        assertNotNull(privateKey.toString())
        assertEquals(privateKey.toString(), "5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3")
    }

    @Test
    fun testPublicKeyFormat() {
        val publicKey = GxcPublicKey("EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV")
        assertEquals("EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV", publicKey.toString())
        val publicKeyFromBytes = GxcPublicKey(publicKey.bytes)
        assertEquals(publicKeyFromBytes.toString(), publicKey.toString())
    }
}