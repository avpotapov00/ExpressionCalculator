package info.potapov.calculator.visitor.impl

import info.potapov.calculator.Tokens
import info.potapov.calculator.token.*
import info.potapov.calculator.visitor.TokenVisitor

fun printExpression(tokens: Tokens) {
    val visitor = PrintVisitor()
    tokens.forEach { it.accept(visitor) }

    println(visitor.result.joinToString(SPACE))
}

private class PrintVisitor: TokenVisitor {

    var result = arrayListOf<String>()

    override fun visit(numberToken: NumberToken) {
        result.add("NUMBER(${numberToken.number})")
    }

    override fun visit(braceToken: BraceToken) {
        when (braceToken) {
            LeftBraceToken -> result.add("LEFT")
            RightBraceToken -> result.add("RIGHT")
        }
    }

    override fun visit(operationToken: OperationToken) {
        val content = when (operationToken) {
            DivOperationToken -> "DIV"
            AddOperationToken -> "PLUS"
            MulOperationToken -> "MUL"
            SubOperationToken -> "SUB"
        }

        result.add(content)
    }

    companion object {

        fun visitAll(tokens: Tokens) {
            val visitor = PrintVisitor()
            tokens.forEach { it.accept(visitor) }

            println(visitor.result.joinToString(SPACE))
        }

    }

}

private const val SPACE = " "
