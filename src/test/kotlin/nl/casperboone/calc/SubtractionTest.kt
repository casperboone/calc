package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class SubtractionTest : Spek({
    describe("the evaluation of a subtraction") {
        it("should parse a subtraction") {
            assertThat(Grammar.parseProgram("1 - 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "-",
                                    Integer(1),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested subtractions left associatively") {
            assertThat(Grammar.parseProgram("1 - 2 - 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "-",
                                    BinaryOperation(
                                            "-",
                                            Integer(1),
                                            Integer(2)
                                    ),
                                    Integer(3)
                            )
                    )
        }

        it("should give precedence to addition statements between braces") {
            assertThat(Grammar.parseProgram("1 - (2 - 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "-",
                                    Integer(1),
                                    BinaryOperation(
                                            "-",
                                            Integer(2),
                                            Integer(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple subtraction") {
            assertThat(evaluate("1 - 2")).isEqualTo(Integer(-1))
        }

        it("should interpret nested addition statements") {
            assertThat(evaluate("1 - 2 - 3")).isEqualTo(Integer(-4))
        }

        it("should interpret a subtraction with floats") {
            assertThat(evaluate("1.3 - 0.5")).isEqualTo(Float(0.8))
        }
    }
})
