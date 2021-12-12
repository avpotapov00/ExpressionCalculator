package info.potapov.calculator.visitor.impl

import info.potapov.calculator.token.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ParserVisitorTest {

    @Test
    fun `should transform notation simple example`() {
        // given
        val given = listOf(
            LeftBraceToken,
            NumberToken(1), AddOperationToken, NumberToken(6), MulOperationToken, NumberToken(7),
            RightBraceToken,

            SubOperationToken,
            LeftBraceToken,
            NumberToken(2), MulOperationToken, NumberToken(4),
            RightBraceToken
        )
        val expected = listOf(
            NumberToken(1),
            NumberToken(6),
            NumberToken(7),
            MulOperationToken,
            AddOperationToken,
            NumberToken(2),
            NumberToken(4),
            MulOperationToken,
            SubOperationToken
        )

        // when
        val result = reverseExpression(given)

        //then
        assertEquals(expected, result)
    }

    @Test
    fun `should transform notation example with braces`() {
        // given
        val expected = listOf(NumberToken(1), NumberToken(2), AddOperationToken)
        val given = listOf(NumberToken(1), AddOperationToken, NumberToken(2))

        // when
        val result = reverseExpression(given)

        //then
        assertEquals(expected, result)
    }

}