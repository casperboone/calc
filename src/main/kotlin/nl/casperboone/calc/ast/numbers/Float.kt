package nl.casperboone.calc.ast.numbers

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor

data class Float(val value: Double) : AstNode {
    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "Float ($value)"
}
