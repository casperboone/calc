package nl.casperboone.calc.expressions.typecheckable

import nl.casperboone.calc.expressions.interpretable.Addition as InterpretableAddition

data class Addition(private val left: TypeCheckableExpression, private val right: TypeCheckableExpression) : TypeCheckableExpression {
    override fun checkTypes() = InterpretableAddition(left.checkTypes(), right.checkTypes())

    override fun toString() = "Addition\n|---$left\n|---$right"
}
