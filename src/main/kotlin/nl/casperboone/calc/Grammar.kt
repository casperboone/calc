package nl.casperboone.calc

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.parser.Parser
import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import nl.casperboone.calc.ast.operations.UnaryOperation

object Grammar : Grammar<AstNode>() {
    private val LPAR by token("\\(")
    private val RPAR by token("\\)")
    private val FLOAT_CONSTANT by token("\\d+\\.\\d+|\\.\\d+|\\d+\\.")
    private val INTEGER_CONSTANT by token("\\d+")
    private val PLUS by token("\\+")
    private val MINUS by token("\\-")
    private val TIMES by token("\\*")
    private val DIVISION by token("/")
    private val POWER by token("\\^")
    private val SQRT_TEXT by token("sqrt")
    private val SQRT_SIGN by token("√")
    private val WHITESPACE by token("\\s+", ignore = true)

    private val bracedExpression by -LPAR * parser(this::expr) * -RPAR

    private val integerExpression: Parser<AstNode> = INTEGER_CONSTANT map { Integer(it.text.toInt()) }
    private val floatExpression: Parser<AstNode> = FLOAT_CONSTANT map { Float(it.text.toDouble()) }

    private val unaryMinusExpression = (-MINUS * parser(this::term)) map { UnaryOperation("-", it) }
    private val unarySqrtTextExpression = (-SQRT_TEXT * parser(this::term)) map { UnaryOperation("sqrt", it) }
    private val unarySqrtSignExpression = (-SQRT_SIGN * parser(this::term)) map { UnaryOperation("√", it) }

    private val term: Parser<AstNode> by
            integerExpression or
            floatExpression or
            unaryMinusExpression or
            unarySqrtTextExpression or
            unarySqrtSignExpression or
            bracedExpression

    private val binaryOperatorTokens = PLUS or MINUS or TIMES or DIVISION or POWER
    private val binaryOperatorChain by leftAssociative(term, binaryOperatorTokens) { a, op, b -> BinaryOperation(op.text, a, b) }

    private val expr = binaryOperatorChain

    override val rootParser by expr

    fun parseProgram(program: String) = this.parseToEnd(program)
}
