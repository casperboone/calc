package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import nl.casperboone.calc.ast.numbers.Integer
import nl.casperboone.calc.ast.operations.UnaryOperation
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class SquareRootTest : Spek({
    describe("the evaluation of a square root operation") {
        it("should parse a square root operation in sqrt style") {
            assertThat(Grammar.parseProgram("sqrt 2"))
                    .isEqualTo(
                            UnaryOperation(
                                    "sqrt",
                                    Integer(2)
                            )
                    )
        }

        it("should parse a square root operation in √ style") {
            assertThat(Grammar.parseProgram("√2"))
                    .isEqualTo(
                            UnaryOperation(
                                    "√",
                                    Integer(2)
                            )
                    )
        }

        it("should interpret a simple square root operation") {
            assertThat(evaluate("sqrt 25")).isEqualTo(Float(5.0))
        }

        it("should interpret a square root operation with floats") {
            assertThat(evaluate("√8.4")).isEqualTo(Float(2.898275349237888))
        }
    }
})
