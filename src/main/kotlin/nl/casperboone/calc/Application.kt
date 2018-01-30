package nl.casperboone.calc

fun main(args: Array<String>) {
    val rawExpression = args.joinToString(separator = "")

    println(evaluate(rawExpression))
}

val passes = arrayOf(Desugarer, Interpreter)

fun evaluate(program: String) = passes.fold(Grammar.parseProgram(program)) { ast, visitor -> ast.accept(visitor) }
