package nl.casperboone.calc

import nl.casperboone.calc.expressions.desugarable.BinaryOperation
import nl.casperboone.calc.values.Integer as IntegerValue
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import nl.casperboone.calc.expressions.desugarable.Integer as DesugarableIntegerExpression

class SubtractionTest : Spek({
    describe("the evaluation of a subtraction") {
        it("should parse a subtraction") {
            assertThat(Grammar.parseProgram("1 - 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "-",
                                    DesugarableIntegerExpression(1),
                                    DesugarableIntegerExpression(2)
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
                                            DesugarableIntegerExpression(1),
                                            DesugarableIntegerExpression(2)
                                    ),
                                    DesugarableIntegerExpression(3)
                            )
                    )
        }

        it("should give precedence to addition statements between braces") {
            assertThat(Grammar.parseProgram("1 - (2 - 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "-",
                                    DesugarableIntegerExpression(1),
                                    BinaryOperation(
                                            "-",
                                            DesugarableIntegerExpression(2),
                                            DesugarableIntegerExpression(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple subtraction") {
            assertThat(evaluate("1 - 2")).isEqualTo(IntegerValue(-1))
        }

        it("should interpret nested addition statements") {
            assertThat(evaluate("1 - 2 - 3")).isEqualTo(IntegerValue(-4))
        }
    }
})
