package nl.casperboone.calc.expressions.desugarable

import nl.casperboone.calc.expressions.typecheckable.Addition as TypeCheckableAddition

data class Addition(private val left: DesugarableExpression, private val right: DesugarableExpression) : DesugarableExpression {
    override fun desugar() = TypeCheckableAddition(left.desugar(), right.desugar())

    override fun toString() = "Addition\n|---$left\n|---$right"
}
