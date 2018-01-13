package nl.casperboone.calc

import nl.casperboone.calc.values.Value

fun main(args: Array<String>) {
    val rawExpression = args.joinToString(separator = "")

    println(evaluate(rawExpression))
}

fun evaluate(program: String): Value {
    val parsedAST = Grammar.parseProgram(program)
    val desugaredAST = parsedAST.desugar()
    val typeCheckedAST = desugaredAST.checkTypes()

    return typeCheckedAST.interpret()
}
