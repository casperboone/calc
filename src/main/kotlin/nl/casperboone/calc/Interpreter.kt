package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.*
import kotlin.math.pow
import kotlin.math.sqrt

object Interpreter : AstVisitor {
    override fun visit(addition: Addition): AstNode {
        val left = addition.left.accept(this)
        val right = addition.right.accept(this)

        return when (determineNumberTypes(left, right)) {
            NumberArguments.ALL_INTEGER -> Integer(asInt(left) + asInt(right))
            NumberArguments.SOME_FLOAT -> Float(asDouble(left) + asDouble(right))
        }
    }

    override fun visit(subtraction: Subtraction): AstNode {
        val left = subtraction.left.accept(this)
        val right = subtraction.right.accept(this)

        return when (determineNumberTypes(left, right)) {
            NumberArguments.ALL_INTEGER -> Integer(asInt(left) - asInt(right))
            NumberArguments.SOME_FLOAT -> Float(asDouble(left) - asDouble(right))
        }
    }

    override fun visit(multiplication: Multiplication): AstNode {
        val left = multiplication.left.accept(this)
        val right = multiplication.right.accept(this)

        return when (determineNumberTypes(left, right)) {
            NumberArguments.ALL_INTEGER -> Integer(asInt(left) * asInt(right))
            NumberArguments.SOME_FLOAT -> Float(asDouble(left) * asDouble(right))
        }
    }

    override fun visit(division: Division) = Float(asDouble(division.left.accept(this)) / asDouble(division.right.accept(this)))

    override fun visit(power: Power) = Float(asDouble(power.left.accept(this)).pow(asDouble(power.right.accept(this))))

    override fun visit(squareRoot: SquareRoot) = Float(sqrt(asDouble(squareRoot.value.accept(this))))

    override fun visit(integer: Integer) = Integer(integer.value)

    override fun visit(float: Float) = Float(float.value)

    override fun visit(binaryOperation: BinaryOperation) = throw Error("Binary operations cannot be interpreted")

    override fun visit(unaryOperation: UnaryOperation) = throw Error("Unary operations cannot be interpreted")
}

enum class NumberArguments {
    ALL_INTEGER, SOME_FLOAT
}

fun determineNumberTypes(vararg arguments: AstNode) = if (arguments.any { it is Float }) NumberArguments.SOME_FLOAT else NumberArguments.ALL_INTEGER

fun asInt(node: AstNode) = when (node) {
    is Integer -> node.value
    else -> throw Error("Supplied argument not allowed for this operation")
}

fun asDouble(node: AstNode): Double = when (node) {
    is Integer -> node.value.toDouble()
    is Float -> node.value
    else -> throw Error("Supplied argument not allowed for this operation")
}
