package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class MultiplicationTest : Spek({
    describe("the evaluation of an multiplication") {
        it("should parse an multiplication") {
            assertThat(Grammar.parseProgram("3 * 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "*",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested multiplications left associatively") {
            assertThat(Grammar.parseProgram("5 * 2 * 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "*",
                                    BinaryOperation(
                                            "*",
                                            Integer(5),
                                            Integer(2)
                                    ),
                                    Integer(3)
                            )
                    )
        }

        it("should give precedence to multiplication statements between braces") {
            assertThat(Grammar.parseProgram("5 * (2 * 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "*",
                                    Integer(5),
                                    BinaryOperation(
                                            "*",
                                            Integer(2),
                                            Integer(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple multiplication") {
            assertThat(evaluate("5 * 2")).isEqualTo(Integer(10))
        }

        it("should interpret nested multiplication statements") {
            assertThat(evaluate("5 * 2 * 3")).isEqualTo(Integer(30))
        }
    }
})
