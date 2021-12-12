package info.potapov.calculator.visitor.impl

import info.potapov.calculator.token.AddOperationToken
import info.potapov.calculator.token.MulOperationToken
import info.potapov.calculator.token.NumberToken
import info.potapov.calculator.token.SubOperationToken
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CalcVisitorTest {

    @Test
    fun `should calculate simple expression`() {
        // given
        val tokens = listOf(NumberToken(1), NumberToken(2), AddOperationToken)

        // when
        val result = calculateExpression(tokens)

        //then
        assertEquals(3.0, result, 1e-4)
    }

    @Test
    fun `should calculate expression with many ops`() {
        // given (1 + 6 * 7) - (2 * 4)
        val tokens = listOf(
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
        val result = calculateExpression(tokens)

        //then
        assertEquals(35.0, result, 1e-4)
    }

}