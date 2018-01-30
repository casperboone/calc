package nl.casperboone.calc.ast

interface AstNode {
    fun accept(visitor: AstVisitor): AstNode

    override fun toString(): String
}
