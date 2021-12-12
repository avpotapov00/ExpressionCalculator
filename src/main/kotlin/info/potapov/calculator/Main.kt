package info.potapov.calculator

import info.potapov.calculator.token.Token
import info.potapov.calculator.tokenizer.impl.TokenizerImpl
import info.potapov.calculator.visitor.impl.*
import kotlin.math.round

typealias Tokens = List<Token>

fun main() {
    val expression = readExpression()

    val tokens = splitExpression(expression)
    printExpression(tokens)

    val reversed = reverseExpression(tokens)
    printExpression(reversed)

    val result = calculateExpression(reversed)

    println("Result: ${withPrecision(result)}")

}

private fun withPrecision(value: Double): Double {
    val precisionFactor = 1000

    return round(value * precisionFactor) / precisionFactor
}

private fun splitExpression(expression: String): Tokens {
    return TokenizerImpl().split(expression)
}

private fun readExpression(): String {
    val expression = readLine()
    require(expression != null && expression.isNotEmpty()) { "Expression can't be null or empty" }

    return expression
}