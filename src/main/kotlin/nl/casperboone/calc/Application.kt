package nl.casperboone.calc

import nl.casperboone.calc.values.Value

fun main(args: Array<String>) {
    val rawExpression = args.joinToString(separator = "")

    println(evaluate(rawExpression))
}

fun evaluate(program: String): Value {
    val AST = Grammar.parseProgram(program)

    return Interpreter.interpret(AST)
}
