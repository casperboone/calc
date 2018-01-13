package nl.casperboone.calc.expressions.interpretable

import nl.casperboone.calc.values.Integer as IntegerValue

data class Integer(private val value: Int) : InterpretableExpression {
    override fun interpret() = IntegerValue(value)

    override fun toString() = "Integer ($value)"
}
