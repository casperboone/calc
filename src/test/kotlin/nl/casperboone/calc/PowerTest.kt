package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class PowerTest : Spek({
    describe("the evaluation of a power operation") {
        it("should parse a power operation") {
            assertThat(Grammar.parseProgram("3^2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "^",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }

        it("should parse a power operation without whitespace") {
            assertThat(Grammar.parseProgram("3 ^ 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "^",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested powers left associatively") {
            assertThat(Grammar.parseProgram("5 ^ 2 ^ 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "^",
                                    BinaryOperation(
                                            "^",
                                            Integer(5),
                                            Integer(2)
                                    ),
                                    Integer(3)
                            )
                    )
        }

        it("should give precedence to power operation statements between braces") {
            assertThat(Grammar.parseProgram("5 ^ (2 ^ 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "^",
                                    Integer(5),
                                    BinaryOperation(
                                            "^",
                                            Integer(2),
                                            Integer(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple power operation") {
            assertThat(evaluate("5 ^ 2")).isEqualTo(Float(25.0))
        }

        it("should interpret nested power statements") {
            assertThat(evaluate("5 ^ 2 ^ 2")).isEqualTo(Float(625.0))
        }
    }
})
