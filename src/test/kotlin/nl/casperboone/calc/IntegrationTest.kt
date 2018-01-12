package nl.casperboone.calc

import nl.casperboone.calc.values.Integer
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class IntegrationTest : Spek({
    describe("a calculator application") {
        it("should return the result of a simple addition") {
            assertThat(evaluate("1 + 2")).isEqualTo(Integer(3))
        }

        it("should return the result of adding nested addition statements") {
            assertThat(evaluate("1 + 2 + 3")).isEqualTo(Integer(6))
        }
    }
})
