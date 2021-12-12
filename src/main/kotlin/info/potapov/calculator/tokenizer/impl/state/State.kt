package info.potapov.calculator.tokenizer.impl.state

import info.potapov.calculator.tokenizer.impl.TokenizerImpl

abstract class State(
    protected val tokenizer: TokenizerImpl
) {

    abstract fun consume(char: Char)

    open fun completeProcessing() {}

}

fun Char.toDigit() = this - '0'
