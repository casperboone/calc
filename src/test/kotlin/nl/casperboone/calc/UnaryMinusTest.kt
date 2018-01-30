package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.Subtraction
import nl.casperboone.calc.ast.operations.UnaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class UnaryMinusTest : Spek({
    describe("the evaluation of a unary minus") {
        it("should parse a unary minus") {
            assertThat(Grammar.parseProgram("-1"))
                    .isEqualTo(
                            UnaryOperation(
                                    "-",
                                    Integer(1)
                            )
                    )
        }

        it("should parse nested unary minus") {
            assertThat(Grammar.parseProgram("--2"))
                    .isEqualTo(
                            UnaryOperation(
                                    "-",
                                    UnaryOperation(
                                            "-",
                                            Integer(2)
                                    )
                            )
                    )
        }

        it("should desugar unary minus to a binary subtraction") {
            assertThat(Grammar.parseProgram("-2").accept(Desugarer))
                    .isEqualTo(
                            Subtraction(
                                    Integer(0),
                                    Integer(2)
                            )
                    )
        }

        it("should interpret a simple unary minus") {
            assertThat(evaluate("-2")).isEqualTo(Integer(-2))
        }

        it("should interpret nested unary minus statements") {
            assertThat(evaluate("--3")).isEqualTo(Integer(3))
        }
    }
})
