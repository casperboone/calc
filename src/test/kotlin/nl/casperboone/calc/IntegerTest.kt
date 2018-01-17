package nl.casperboone.calc

import nl.casperboone.calc.values.Integer as IntegerValue
import nl.casperboone.calc.expressions.desugarable.Integer as DesugarableIntegerExpression
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class IntegerTest : Spek({
    describe("the evaluation of an integer") {
        it("should parse an integer") {
            assertThat(Grammar.parseProgram("1")).isEqualTo(DesugarableIntegerExpression(1))
        }

        it("should interpret an integer") {
            assertThat(evaluate("3")).isEqualTo(IntegerValue(3))
        }
    }
})
