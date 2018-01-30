package nl.casperboone.calc.ast.numbers

import nl.casperboone.calc.ast.*

data class Integer(val value: Int) : AstNode {

    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "Integer ($value)"
}
