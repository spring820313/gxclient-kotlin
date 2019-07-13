package gxchain.encoder

interface Serializable {
    fun serialize(encoder: Encoder):Boolean
}