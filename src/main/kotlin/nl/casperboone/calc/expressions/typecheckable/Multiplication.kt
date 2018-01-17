package nl.casperboone.calc.expressions.typecheckable

import nl.casperboone.calc.expressions.interpretable.Multiplication as InterpretableMultiplication

data class Multiplication(private val left: TypeCheckableExpression, private val right: TypeCheckableExpression) : TypeCheckableExpression {
    override fun checkTypes() = InterpretableMultiplication(left.checkTypes(), right.checkTypes())

    override fun toString() = "Multiplication\n|---$left\n|---$right"
}
