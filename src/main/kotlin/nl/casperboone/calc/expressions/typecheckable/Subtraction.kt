package nl.casperboone.calc.expressions.typecheckable

import nl.casperboone.calc.expressions.interpretable.Subtraction as InterpretableSubtraction

data class Subtraction(private val left: TypeCheckableExpression, private val right: TypeCheckableExpression) : TypeCheckableExpression {
    override fun checkTypes() = InterpretableSubtraction(left.checkTypes(), right.checkTypes())

    override fun toString() = "Addition\n|---$left\n|---$right"
}
