package nl.casperboone.calc.expressions.desugarable

import nl.casperboone.calc.expressions.Expression
import nl.casperboone.calc.expressions.typecheckable.TypeCheckableExpression

interface DesugarableExpression : Expression {
    fun desugar(): TypeCheckableExpression
}
