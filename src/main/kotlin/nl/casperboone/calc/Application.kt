package nl.casperboone.calc

import nl.casperboone.calc.ast.AstNode
import nl.casperboone.calc.ast.Outputtable

fun main(args: Array<String>) = output(evaluate(args.joinToString(separator = "")))

fun output(astNode: AstNode) = if (astNode is Outputtable) println(astNode.outputText()) else println("Invalid result.\n $astNode")

val passes = arrayOf(Desugarer, Interpreter)

fun evaluate(program: String) = passes.fold(Grammar.parseProgram(program)) { ast, visitor -> ast.accept(visitor) }
