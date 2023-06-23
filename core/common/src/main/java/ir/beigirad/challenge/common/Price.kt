package ir.beigirad.challenge.common

@JvmInline
value class Price(private val value: UInt) {
    fun asNumber(): Number = value.toInt()
}
