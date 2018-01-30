package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class DivisionTest : Spek({
    describe("the evaluation of a division") {
        it("should parse a division") {
            assertThat(Grammar.parseProgram("3 / 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "/",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested divisions left associatively") {
            assertThat(Grammar.parseProgram("5 / 2 / 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "/",
                                    BinaryOperation(
                                            "/",
                                            Integer(5),
                                            Integer(2)
                                    ),
                                    Integer(3)
                            )
                    )
        }

        it("should give precedence to division statements between braces") {
            assertThat(Grammar.parseProgram("5 / (2 / 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "/",
                                    Integer(5),
                                    BinaryOperation(
                                            "/",
                                            Integer(2),
                                            Integer(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple division") {
            assertThat(evaluate("10 / 2")).isEqualTo(Integer(5))
        }

        it("should interpret nested multiplication statements") {
            assertThat(evaluate("20 / 2 / 2")).isEqualTo(Integer(5))
        }

        it("should interpret a division with floats") {
            assertThat(evaluate("10.5 / 2.3")).isEqualTo(Float(4.565217391304349))
        }
    }
})
