package info.potapov.calculator.token

import info.potapov.calculator.visitor.TokenVisitor


sealed class OperationToken(
    val symbol: Char
) : Token {

    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BraceToken

        if (symbol != other.symbol) return false

        return true
    }

    override fun hashCode(): Int {
        return symbol.hashCode()
    }


}

object AddOperationToken : OperationToken('+')
object SubOperationToken : OperationToken('-')
object MulOperationToken : OperationToken('*')
object DivOperationToken : OperationToken('/')

