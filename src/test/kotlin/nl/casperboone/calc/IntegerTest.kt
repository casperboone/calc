package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Integer
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class IntegerTest : Spek({
    describe("the evaluation of an integer") {
        it("should parse an integer") {
            assertThat(Grammar.parseProgram("1")).isEqualTo(Integer(1))
        }

        it("should interpret an integer") {
            assertThat(evaluate("3")).isEqualTo(Integer(3))
        }
    }
})
