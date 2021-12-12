package info.potapov.calculator.tokenizer.impl.state

import info.potapov.calculator.token.NumberToken
import info.potapov.calculator.tokenizer.impl.TokenizerImpl

class NumberState(
    tokenizerImpl: TokenizerImpl,
    startNumber: Int
): State(tokenizerImpl) {

    private var currentNumber = startNumber

    override fun consume(char: Char) {
        if (char in '0'..'9') {
            currentNumber = currentNumber * 10 + char.toDigit()
        } else {
            addCurrentNumberToken()
            tokenizer.changeState(SymbolState(tokenizer).also { it.consume(char) })
        }
    }

    override fun completeProcessing() {
        addCurrentNumberToken()
    }

    private fun addCurrentNumberToken() {
        tokenizer.createToken(NumberToken(currentNumber))
    }
}


