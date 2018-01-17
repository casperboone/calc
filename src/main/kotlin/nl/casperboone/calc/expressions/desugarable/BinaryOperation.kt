package nl.casperboone.calc.expressions.desugarable

import nl.casperboone.calc.expressions.typecheckable.Addition as TypeCheckableAddition
import nl.casperboone.calc.expressions.typecheckable.Subtraction as TypeCheckableSubtraction

data class BinaryOperation(private val operation: String, private val left: DesugarableExpression, private val right: DesugarableExpression) : DesugarableExpression {
    override fun desugar() = when (operation) {
        "+" -> TypeCheckableAddition(left.desugar(), right.desugar())
        "-" -> TypeCheckableSubtraction(left.desugar(), right.desugar())
        else -> error("Binary operation $operation is not supported")
    }

    override fun toString() = "BinaryOperation ($operation)\n|---$left\n|---$right"
}

