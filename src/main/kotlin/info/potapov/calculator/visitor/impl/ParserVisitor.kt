package info.potapov.calculator.visitor.impl

import info.potapov.calculator.Tokens
import info.potapov.calculator.token.*
import info.potapov.calculator.visitor.TokenVisitor
import java.util.*

fun reverseExpression(tokens: Tokens): Tokens {
    val visitor = ParserVisitor()
    val result = arrayListOf<Token>()

    tokens.forEach { it.accept(visitor) }

    result.addAll(visitor.reversePolandNotation)

    while (visitor.stack.isNotEmpty()) {
        val elem = visitor.stack.pop()
        if (elem !is BraceToken) {
            result.add(elem)
        }
    }

    return result
}

private class ParserVisitor : TokenVisitor {

    val reversePolandNotation = arrayListOf<Token>()

    val stack: Deque<Token> = ArrayDeque()

    override fun visit(numberToken: NumberToken) {
        reversePolandNotation.add(numberToken)
    }

    override fun visit(braceToken: BraceToken) {
        when (braceToken) {
            RightBraceToken -> visitRightBrace()
            LeftBraceToken -> visitLeftBrace(braceToken)
        }
    }

    private fun visitLeftBrace(braceToken: BraceToken) {
        stack.push(braceToken)
    }

    private fun visitRightBrace() {
        while (stack.isNotEmpty() && lastIsLeftBrace()) {
            reversePolandNotation.add(stack.pop())
        }

        if (stack.isEmpty()) {
            error("Braces mismatched")
        } else {
            stack.pop()
        }
    }

    private fun lastIsLeftBrace() = stack.first() !== LeftBraceToken

    override fun visit(operationToken: OperationToken) {
        addFromStack(operationToken)
        stack.push(operationToken)
    }

    private fun addFromStack(operationToken: OperationToken) {
        while (stack.isNotEmpty()
            && stack.peek() is OperationToken
            && !isRankLess(stack.peek() as OperationToken, operationToken)
        ) {
            reversePolandNotation.add(stack.pop())
        }
    }

    private fun isRankLess(l: OperationToken, r: OperationToken): Boolean {
        return (l === AddOperationToken || l === SubOperationToken)
                && (r === MulOperationToken || r === DivOperationToken)
    }

}