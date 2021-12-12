package info.potapov.calculator.tokenizer.impl.state

import info.potapov.calculator.token.*
import info.potapov.calculator.tokenizer.impl.TokenizerImpl

class SymbolState(
    tokenizerImpl: TokenizerImpl
): State(tokenizerImpl) {

    override fun consume(char: Char) {
        if (char.isWhitespace()) return

        if (char.isDigit()) {
            tokenizer.changeState(NumberState(tokenizer, char.toDigit()))
            return
        }

        when (char) {
            '(' -> tokenizer.createToken(LeftBraceToken)
            ')' -> tokenizer.createToken(RightBraceToken)

            '+' -> tokenizer.createToken(AddOperationToken)
            '-' -> tokenizer.createToken(SubOperationToken)
            '*' -> tokenizer.createToken(MulOperationToken)
            '/' -> tokenizer.createToken(DivOperationToken)

            else -> error("Can't parse expression: unknown character: $char")
        }

    }

}
