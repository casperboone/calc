package nl.casperboone.calc.expressions

import nl.casperboone.calc.values.Integer

data class Integer(private val value: Int) : Expression {
    override fun interpret() = Integer(value)

    override fun toString() = "Integer ($value)"
}
