package nl.casperboone.calc

import nl.casperboone.calc.expressions.desugarable.BinaryOperation
import nl.casperboone.calc.values.Integer
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class AdditionTest : Spek({
    describe("the evaluation of an addition") {
        it("should parse an addition") {
            assertThat(Grammar.parseProgram("1 + 2"))
                    .isEqualTo(
                            BinaryOperation(
                                    "+",
                                    nl.casperboone.calc.expressions.desugarable.Integer(1),
                                    nl.casperboone.calc.expressions.desugarable.Integer(2)
                            )
                    )
        }

        it("should parse nested additions left associatively") {
            assertThat(Grammar.parseProgram("1 + 2 + 3"))
                    .isEqualTo(
                            BinaryOperation(
                                    "+",
                                    BinaryOperation(
                                            "+",
                                            nl.casperboone.calc.expressions.desugarable.Integer(1),
                                            nl.casperboone.calc.expressions.desugarable.Integer(2)
                                    ),
                                    nl.casperboone.calc.expressions.desugarable.Integer(3)
                            )
                    )
        }

        it("should give precedence to addition statements between braces") {
            assertThat(Grammar.parseProgram("1 + (2 + 3)"))
                    .isEqualTo(
                            BinaryOperation(
                                    "+",
                                    nl.casperboone.calc.expressions.desugarable.Integer(1),
                                    BinaryOperation(
                                            "+",
                                            nl.casperboone.calc.expressions.desugarable.Integer(2),
                                            nl.casperboone.calc.expressions.desugarable.Integer(3)
                                    )
                            )
                    )
        }

        it("should interpret a simple addition") {
            assertThat(evaluate("1 + 2")).isEqualTo(Integer(3))
        }

        it("should interpret nested addition statements") {
            assertThat(evaluate("1 + 2 + 3")).isEqualTo(Integer(6))
        }
    }
})
