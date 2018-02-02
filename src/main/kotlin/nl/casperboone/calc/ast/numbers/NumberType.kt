package nl.casperboone.calc.ast.numbers

import nl.casperboone.calc.ast.AstNode

interface NumberType : AstNode{
    operator fun plus(other: NumberType): NumberType

    operator fun minus(other: NumberType): NumberType

    operator fun times(other: NumberType): NumberType
}
