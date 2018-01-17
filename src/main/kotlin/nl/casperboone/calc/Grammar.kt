package nl.casperboone.calc

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.parser.Parser
import nl.casperboone.calc.expressions.desugarable.BinaryOperation
import nl.casperboone.calc.expressions.desugarable.DesugarableExpression
import nl.casperboone.calc.expressions.desugarable.Integer
import nl.casperboone.calc.expressions.desugarable.UnaryOperation

object Grammar : Grammar<DesugarableExpression>() {
    private val LPAR by token("\\(")
    private val RPAR by token("\\)")
    private val INTEGER_CONSTANT by token("\\d+")
    private val PLUS by token("\\+")
    private val MINUS by token("\\-")
    private val TIMES by token("\\*")
    private val WHITESPACE by token("\\s+", ignore = true)

    private val unaryMinusExpression = (-MINUS * parser(this::term)) map { UnaryOperation("-", it) }
    private val bracedExpression by -LPAR * parser(this::expr) * -RPAR

    private val term: Parser<DesugarableExpression> by
            (INTEGER_CONSTANT map { Integer(it.text.toInt()) }) or
            unaryMinusExpression or
            bracedExpression

    private val binaryOperatorTokens = PLUS or MINUS or TIMES
    private val binaryOperatorChain by leftAssociative(term, binaryOperatorTokens) { a, op, b -> BinaryOperation(op.text, a, b)  }

    private val expr = binaryOperatorChain

    override val rootParser by expr

    fun parseProgram(program: String) = this.parseToEnd(program)
}
