package nl.casperboone.calc.expressions

import nl.casperboone.calc.values.Value

interface Expression {
    fun interpret(): Value
    override fun toString(): String
}
