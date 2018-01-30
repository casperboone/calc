package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.*

class Interpreter : AstVisitor {
    override fun visit(subtraction: Subtraction) = Integer(asInt(subtraction.left.accept(this)) - asInt(subtraction.right.accept(this)))

    override fun visit(multiplication: Multiplication) = Integer(asInt(multiplication.left.accept(this)) * asInt(multiplication.right.accept(this)))

    override fun visit(addition: Addition) = Integer(asInt(addition.left.accept(this)) + asInt(addition.right.accept(this)))

    override fun visit(integer: Integer) = Integer(integer.value)

    override fun visit(binaryOperation: BinaryOperation) = throw Error("Binary operations cannot be interpreted")

    override fun visit(unaryOperation: UnaryOperation) = throw Error("Unary operations cannot be interpreted")
}

fun asInt(node: AstNode) : Int {
    if (node !is Integer) throw Error("Supplied argument not allowed for this operation")
    return node.value
}
