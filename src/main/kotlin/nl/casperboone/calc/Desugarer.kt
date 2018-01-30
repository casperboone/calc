package nl.casperboone.calc

import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.*

class Desugarer : AstVisitor {
    override fun visit(integer: Integer) = Integer(integer.value)

    override fun visit(unaryOperation: UnaryOperation) = when(unaryOperation.operation) {
        "-" -> Subtraction(Integer(0), unaryOperation.value.accept(this))
        else -> throw Error("Unary operation ${unaryOperation.operation} is not supported")
    }

    override fun visit(binaryOperation: BinaryOperation) = when(binaryOperation.operation) {
        "+" -> Addition(binaryOperation.left.accept(this), binaryOperation.right.accept(this))
        "-" -> Subtraction(binaryOperation.left.accept(this), binaryOperation.right.accept(this))
        "*" -> Multiplication(binaryOperation.left.accept(this), binaryOperation.right.accept(this))
        else -> throw Error("Binary operation ${binaryOperation.operation} is not supported")
    }

    override fun visit(addition: Addition) = throw Error("Addition operation cannot be desugared")

    override fun visit(subtraction: Subtraction) = throw Error("Subtraction operation cannot be desugared")

    override fun visit(multiplication: Multiplication) = throw Error("Multiplication operation cannot be desugared")


}
