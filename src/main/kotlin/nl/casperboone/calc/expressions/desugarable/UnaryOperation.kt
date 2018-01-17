package nl.casperboone.calc.expressions.desugarable

import nl.casperboone.calc.expressions.typecheckable.Integer as TypeCheckableInteger
import nl.casperboone.calc.expressions.typecheckable.Addition as TypeCheckableAddition
import nl.casperboone.calc.expressions.typecheckable.Subtraction as TypeCheckableSubtraction

data class UnaryOperation(private val operation: String, private val value: DesugarableExpression) : DesugarableExpression {
    override fun desugar() = when (operation) {
        "-" -> TypeCheckableSubtraction(TypeCheckableInteger(0), value.desugar())
        else -> error("Unary operation $operation is not supported")
    }

    override fun toString() = "UnaryOperation ($operation)\n|---$value\n"
}

