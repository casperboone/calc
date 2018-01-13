package nl.casperboone.calc.expressions.interpretable

import nl.casperboone.calc.expressions.Expression
import nl.casperboone.calc.values.Value

interface InterpretableExpression : Expression {
    fun interpret(): Value
}
