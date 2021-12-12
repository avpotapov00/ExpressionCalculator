package info.potapov.calculator.token

import info.potapov.calculator.visitor.TokenVisitor

sealed interface Token {

    fun accept(tokenVisitor: TokenVisitor)

}