package nl.casperboone.calc.ast.operations

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor

data class Multiplication(val left: AstNode, val right: AstNode) : AstNode {
    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "Multiplication \n|---$left\n|---$right"
}

