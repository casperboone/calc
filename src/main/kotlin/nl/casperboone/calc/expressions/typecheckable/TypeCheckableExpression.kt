package nl.casperboone.calc.expressions.typecheckable

import nl.casperboone.calc.expressions.Expression
import nl.casperboone.calc.expressions.interpretable.InterpretableExpression

interface TypeCheckableExpression : Expression {
    fun checkTypes(): InterpretableExpression
}
