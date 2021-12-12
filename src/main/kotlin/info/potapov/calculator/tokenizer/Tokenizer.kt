package info.potapov.calculator.tokenizer

import info.potapov.calculator.token.Token

interface Tokenizer {

    fun split(expression: String): List<Token>
}