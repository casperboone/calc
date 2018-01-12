package nl.casperboone.calc.expressions

import nl.casperboone.calc.values.Integer
import nl.casperboone.calc.values.Value

data class Addition(private val left: Expression, private val right: Expression) : Expression {
    override fun interpret(): Value {
        val leftInterpreted = left.interpret()
        val rightInterpreted = right.interpret()

        // TODO: Deal with different number types in a more appropriate manner
        if (leftInterpreted is Integer && rightInterpreted is Integer)
            return Integer(leftInterpreted.value + rightInterpreted.value)

        return Integer(-1)
    }

    override fun toString() = "Addition\n|---$left\n|---$right"
}