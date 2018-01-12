package nl.casperboone.calc

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.parser.Parser
import nl.casperboone.calc.expressions.Addition
import nl.casperboone.calc.expressions.Expression
import nl.casperboone.calc.expressions.Integer

object Grammar : Grammar<Expression>() {
    private val LPAR by token("\\(")
    private val RPAR by token("\\)")
    private val INTEGER_CONSTANT by token("\\d+")
    private val ADDITION by token("\\+")
    private val WHITESPACE by token("\\s+", ignore = true)

    private val bracedExpression by -LPAR * parser(this::expr) * -RPAR

    private val term: Parser<Expression> by
            (INTEGER_CONSTANT map { Integer(it.text.toInt()) }) or
            bracedExpression

    private val addChain by leftAssociative(term, ADDITION) { a, _, b -> Addition(a, b) }

    private val expr = addChain

    override val rootParser by expr

    fun parseProgram(program: String) = this.parseToEnd(program)
}
