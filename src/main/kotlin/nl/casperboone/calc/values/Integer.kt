package nl.casperboone.calc.values

data class Integer(val value: Int) : Value {
    override fun toString() = "<Integer Value | $value>"
}
