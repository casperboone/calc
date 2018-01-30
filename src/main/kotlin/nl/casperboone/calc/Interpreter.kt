package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.*
import kotlin.math.pow
import kotlin.math.sqrt

object Interpreter : AstVisitor {
    override fun visit(addition: Addition) = when (determineNumberTypes(addition.left, addition.right)) {
        NumberArguments.ALL_INTEGER -> Integer(asInt(addition.left.accept(this)) + asInt(addition.right.accept(this)))
        NumberArguments.SOME_FLOAT -> Float(asDouble(addition.left.accept(this)) + asDouble(addition.right.accept(this)))
    }

    override fun visit(subtraction: Subtraction) = when (determineNumberTypes(subtraction.left, subtraction.right)) {
        NumberArguments.ALL_INTEGER -> Integer(asInt(subtraction.left.accept(this)) - asInt(subtraction.right.accept(this)))
        NumberArguments.SOME_FLOAT -> Float(asDouble(subtraction.left.accept(this)) - asDouble(subtraction.right.accept(this)))
    }

    override fun visit(multiplication: Multiplication) = when (determineNumberTypes(multiplication.left, multiplication.right)) {
        NumberArguments.ALL_INTEGER -> Integer(asInt(multiplication.left.accept(this)) * asInt(multiplication.right.accept(this)))
        NumberArguments.SOME_FLOAT -> Float(asDouble(multiplication.left.accept(this)) * asDouble(multiplication.right.accept(this)))
    }

    override fun visit(division: Division) = when (determineNumberTypes(division.left, division.right)) {
        NumberArguments.ALL_INTEGER -> Integer(asInt(division.left.accept(this)) / asInt(division.right.accept(this)))
        NumberArguments.SOME_FLOAT -> Float(asDouble(division.left.accept(this)) / asDouble(division.right.accept(this)))
    }

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
