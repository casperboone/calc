package nl.casperboone.calc

import nl.casperboone.calc.expressions.desugarable.BinaryOperation as DesugarableBinaryOperation
import nl.casperboone.calc.expressions.desugarable.UnaryOperation as DesugarableUnaryOperation
import nl.casperboone.calc.expressions.typecheckable.Subtraction as TypeCheckableSubtraction
import nl.casperboone.calc.values.Integer as IntegerValue
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import nl.casperboone.calc.expressions.desugarable.Integer as DesugarableInteger
import nl.casperboone.calc.expressions.typecheckable.Integer as TypeCheckableInteger

class UnaryMinusTest : Spek({
    describe("the evaluation of a unary minus") {
        it("should parse a unary minus") {
            assertThat(Grammar.parseProgram("-1"))
                    .isEqualTo(
                            DesugarableUnaryOperation(
                                    "-",
                                    DesugarableInteger(1)
                            )
                    )
        }

        it("should parse nested unary minus") {
            assertThat(Grammar.parseProgram("--2"))
                    .isEqualTo(
                            DesugarableUnaryOperation(
                                    "-",
                                    DesugarableUnaryOperation(
                                            "-",
                                            DesugarableInteger(2)
                                    )
                            )
                    )
        }

        it("should desugar unary minus to a binary subtraction") {
            assertThat(Grammar.parseProgram("-2").desugar())
                    .isEqualTo(
                            TypeCheckableSubtraction(
                                    TypeCheckableInteger(0),
                                    TypeCheckableInteger(2)
                            )
                    )
        }

        it("should interpret a simple unary minus") {
            assertThat(evaluate("-2")).isEqualTo(IntegerValue(-2))
        }

        it("should interpret nested unary minus statements") {
            assertThat(evaluate("--3")).isEqualTo(IntegerValue(3))
        }
    }
})
