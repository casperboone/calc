package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.numbers.NumberType
import nl.casperboone.calc.ast.operations.*
import kotlin.math.pow
import kotlin.math.sqrt

object Interpreter : AstVisitor {
    override fun visit(addition: Addition) = interpretAsNumber(addition.left) + interpretAsNumber(addition.right)

    override fun visit(subtraction: Subtraction) = interpretAsNumber(subtraction.left) - interpretAsNumber(subtraction.right)

    override fun visit(multiplication: Multiplication) = interpretAsNumber(multiplication.left) * interpretAsNumber(multiplication.right)

    override fun visit(division: Division) = Float(asDouble(interpret(division.left)) / asDouble(interpret(division.right)))

    override fun visit(power: Power) = Float(asDouble(interpret(power.left)).pow(asDouble(interpret(power.right))))

    override fun visit(squareRoot: SquareRoot) = Float(sqrt(asDouble(interpret(squareRoot.value))))

    override fun visit(integer: Integer) = Integer(integer.value)

    override fun visit(float: Float) = Float(float.value)

    override fun visit(binaryOperation: BinaryOperation) = throw Error("Binary operations cannot be interpreted")

    override fun visit(unaryOperation: UnaryOperation) = throw Error("Unary operations cannot be interpreted")

    private fun asDouble(node: AstNode): Double = when (node) {
        is Integer -> node.value.toDouble()
        is Float -> node.value
        else -> throw Error("Supplied argument not allowed for this operation")
    }

    private fun interpret(node: AstNode): AstNode = node.accept(this)
    private fun interpretAsNumber(node: AstNode): NumberType = interpret(node) as NumberType
}
