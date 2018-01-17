package nl.casperboone.calc

import nl.casperboone.calc.values.Integer
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class IntegrationTest : Spek({
    describe("a calculator application") {
        it("should return the result of a combined addition and subtraction") {
            assertThat(evaluate("5 + 2 - 4")).isEqualTo(Integer(3))
        }
    }
})
