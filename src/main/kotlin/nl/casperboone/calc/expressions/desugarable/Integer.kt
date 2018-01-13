package nl.casperboone.calc.expressions.desugarable

import nl.casperboone.calc.expressions.typecheckable.Integer as TypeCheckableInteger


data class Integer(private val value: Int) : DesugarableExpression {
    override fun desugar() = TypeCheckableInteger(value)

    override fun toString() = "Integer ($value)"
}
