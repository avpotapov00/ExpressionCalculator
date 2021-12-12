package info.potapov.calculator.token

import info.potapov.calculator.visitor.TokenVisitor

data class NumberToken(
    val number: Int
): Token {

    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

}