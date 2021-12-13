package info.potapov.calculator.visitor.impl

import info.potapov.calculator.Tokens
import info.potapov.calculator.token.*
import info.potapov.calculator.visitor.TokenVisitor
import java.util.function.DoubleBinaryOperator

fun calculateExpression(tokens: Tokens): Double {
    val visitor = CalcVisitor()

    tokens.forEach { it.accept(visitor) }

    return visitor.pop()
}

private class CalcVisitor : TokenVisitor {

    val stack = ArrayDeque<Double>()

    override fun visit(numberToken: NumberToken) {
        stack.add(numberToken.number.toDouble())
    }

    override fun visit(braceToken: BraceToken) {
        error("There is no braces in reversed poland notation")
    }

    override fun visit(operationToken: OperationToken) = when (operationToken) {
        AddOperationToken -> mergeValues(Double::plus)
        SubOperationToken -> mergeValues(Double::minus)
        MulOperationToken -> mergeValues(Double::times)
        DivOperationToken -> mergeValues(Double::div)
    }

    fun mergeValues(action: DoubleBinaryOperator) {
        val first = pop()
        val second = pop()

        val result = action.applyAsDouble(second, first)

        stack.add(result)
    }

    fun pop(): Double {
        return stack.removeLastOrNull() ?: throw IllegalStateException("Incorrect notation")
    }

}