package nl.casperboone.calc

import nl.casperboone.calc.expressions.Expression
import nl.casperboone.calc.values.Value

object Interpreter {
    fun interpret(expression: Expression): Value = expression.interpret()
}
