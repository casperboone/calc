package nl.casperboone.calc.expressions.typecheckable

import nl.casperboone.calc.expressions.interpretable.Integer as InterpretableInteger

data class Integer(private val value: Int) : TypeCheckableExpression {
    override fun checkTypes() = InterpretableInteger(value)

    override fun toString() = "Integer ($value)"
}
