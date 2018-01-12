package nl.casperboone.calc

import nl.casperboone.calc.expressions.Addition
import nl.casperboone.calc.expressions.Integer
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class ParserTest : Spek({
    describe("a math expression parser") {
        it("should parse an integer") {
            assertThat(Grammar.parseProgram("1")).isEqualTo(Integer(1))
        }

        it("should parse an addition") {
            assertThat(Grammar.parseProgram("1 + 2"))
                    .isEqualTo(
                            Addition(
                                    Integer(1),
                                    Integer(2)
                            )
                    )
        }

        it("should parse nested additions left associatively") {
            assertThat(Grammar.parseProgram("1 + 2 + 3"))
                    .isEqualTo(
                            Addition(
                                    Addition(
                                            Integer(1),
                                            Integer(2)
                                    ),
                                    Integer(3)
                            )
                    )
        }

        it("should give precedence to addition statements between braces") {
            assertThat(Grammar.parseProgram("1 + (2 + 3)"))
                    .isEqualTo(
                            Addition(
                                    Integer(1),
                                    Addition(
                                            Integer(2),
                                            Integer(3)
                                    )
                            )
                    )
        }
    }
})
