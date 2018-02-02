package nl.casperboone.calc.ast.numbers

import nl.casperboone.calc.ast.AstVisitor
import nl.casperboone.calc.ast.Outputtable

data class Float(val value: Double) : Outputtable, NumberType {
    override fun accept(visitor: AstVisitor) = visitor.visit(this)

    override fun toString() = "Float ($value)"

    override fun outputText() = "$value"

    override fun plus(other: NumberType): NumberType = when (other) {
        is Integer -> Float(value + other.value)
        is Float -> Float(value + other.value)
        else -> throw Error("Integer addition does not support this number type.")
    }

    override fun minus(other: NumberType): NumberType = when (other) {
        is Integer -> Float(value - other.value)
        is Float -> Float(value - other.value)
        else -> throw Error("Integer subtraction does not support this number type.")
    }

    override fun times(other: NumberType): NumberType = when (other) {
        is Integer -> Float(value * other.value)
        is Float -> Float(value * other.value)
        else -> throw Error("Integer times does not support this number type.")
    }
}
