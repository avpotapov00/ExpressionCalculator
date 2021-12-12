package info.potapov.calculator.tokenizer.impl

import info.potapov.calculator.token.Token
import info.potapov.calculator.tokenizer.Tokenizer
import info.potapov.calculator.tokenizer.impl.state.SymbolState
import info.potapov.calculator.tokenizer.impl.state.State

class TokenizerImpl : Tokenizer {

    private val tokens: MutableList<Token> = arrayListOf()

    private var state: State = SymbolState(this)

    override fun split(expression: String): List<Token> {
        state = SymbolState(this)

        expression.forEach { char ->
            state.consume(char)
        }

        state.completeProcessing()

        return tokens
    }

    fun changeState(state: State) {
        this.state = state
    }

    fun createToken(token: Token) {
        tokens.add(token)
    }

}
