package nl.casperboone.calc.ast.numbers

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.Outputtable

data class Integer(val value: Int) : AstNode, Outputtable {

    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "Integer ($value)"

    override fun outputText() = "$value"
}
