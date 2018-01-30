package nl.casperboone.calc

import nl.casperboone.calc.ast.numbers.Float
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class FloatTest : Spek({
    describe("the evaluation of a float") {
        it("should parse a float in *.* style") {
            assertThat(Grammar.parseProgram("1.6")).isEqualTo(Float(1.6))
        }

        it("should parse a float in .* style") {
            assertThat(Grammar.parseProgram(".6")).isEqualTo(Float(0.6))
        }

        it("should parse a float in *. style") {
            assertThat(Grammar.parseProgram("6.")).isEqualTo(Float(6.0))
        }

        it("should interpret a float") {
            assertThat(evaluate("3.5")).isEqualTo(Float(3.5))
        }
    }
})
