package nl.casperboone.calc.ast

import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.*

interface AstVisitor {
    fun visit(integer: Integer): AstNode
    fun visit(binaryOperation: BinaryOperation): AstNode
    fun visit(unaryOperation: UnaryOperation): AstNode
    fun visit(addition: Addition): AstNode
    fun visit(subtraction: Subtraction): AstNode
    fun visit(multiplication: Multiplication): AstNode
}
