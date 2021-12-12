package info.potapov.calculator.tokenizer.impl

import info.potapov.calculator.token.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

internal class TokenizerImplTest {

    @Test
    fun `should split simple expression`() {
        // given
        val tokenizer = TokenizerImpl()

        // when
        val tokens = tokenizer.split("1 + 2")

        // then
        assertEquals(
            listOf(
                NumberToken(1),
                AddOperationToken,
                NumberToken(2)
            ),
            tokens
        )
    }

    @Test
    fun `should split expression with braces`() {
        // given
        val tokenizer = TokenizerImpl()

        // when
        val tokens = tokenizer.split("(13 * 2) - 12")

        // then
        assertEquals(
            listOf(
                LeftBraceToken,
                NumberToken(13),
                MulOperationToken,
                NumberToken(2),
                RightBraceToken,
                SubOperationToken,
                NumberToken(12)
            ),
            tokens
        )
    }

    @Test
    fun `should fail with exception`() {
        // given
        val tokenizer = TokenizerImpl()

        // when & then
        val exception = assertThrows<IllegalStateException> { tokenizer.split("(13 * 2) -! 12") }

        assertEquals("Can't parse expression: unknown character: !", exception.message)
    }

}