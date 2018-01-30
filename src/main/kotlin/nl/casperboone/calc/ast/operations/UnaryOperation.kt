package nl.casperboone.calc.ast.operations

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor

data class UnaryOperation(val operation: String, val value: AstNode) : AstNode {
    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "UnaryOperation ($operation)\n|---$value"
}

