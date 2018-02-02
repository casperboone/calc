package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.BinaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class ModuloTest : Spek({
    describe("the evaluation of a modulo operation") {
        it("should parse a modulo operation with mod") {
            assertThat(Grammar.parseProgram("3 mod 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "mod",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }
        it("should parse a modulo operation with %") {
            assertThat(Grammar.parseProgram("3 % 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "%",
                                    Integer(3),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested divisions left associatively") {
            assertThat(Grammar.parseProgram("5 mod 2 mod 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "mod",
                                    BinaryOperation(
                                            "mod",
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

        it("should interpret a simple modulo") {
            assertThat(evaluate("12 mod 10")).isEqualTo(Integer(2))
        }

        it("should interpret nested times statements") {
            assertThat(evaluate("16 mod 10 mod 3")).isEqualTo(Integer(0))
        }
    }
})
