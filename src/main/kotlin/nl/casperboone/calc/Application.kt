package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import sun.security.krb5.internal.crypto.Des

fun main(args: Array<String>) {
    val rawExpression = args.joinToString(separator = "")

    println(evaluate(rawExpression))
}

fun evaluate(program: String): AstNode {
    val parsedAST = Grammar.parseProgram(program)
    val desugaredAst = parsedAST.accept(Desugarer())
    return desugaredAst.accept(Interpreter())
//    val desugaredAST = parsedAST.desugar()
//    val typeCheckedAST = desugaredAST.checkTypes()
}
