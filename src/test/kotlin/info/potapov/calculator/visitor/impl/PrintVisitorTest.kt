package info.potapov.calculator.visitor.impl

import info.potapov.calculator.Tokens
import info.potapov.calculator.token.AddOperationToken
import info.potapov.calculator.token.MulOperationToken
import info.potapov.calculator.token.NumberToken
import info.potapov.calculator.token.SubOperationToken
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

internal class PrintVisitorTest {

    @Test
    fun `should print expression`() {
        // given
        val tokens = listOf(NumberToken(1), NumberToken(2), AddOperationToken)

        // when
        val result = collectOutput(tokens)

        //then
        assertEquals("NUMBER(1) NUMBER(2) PLUS".withSeparator(), result)
    }

    @Test
    fun `should print expression with many ops`() {
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
        val result = collectOutput(tokens)

        //then
        assertEquals(
            "NUMBER(1) NUMBER(6) NUMBER(7) MUL PLUS NUMBER(2) NUMBER(4) MUL SUB".withSeparator(),
            result
        )
    }


    private fun String.withSeparator() = this + System.lineSeparator()

    private fun collectOutput(tokens: Tokens): String {
        val systemOut = System.out
        val array = ByteArrayOutputStream()
        val printWriter = PrintStream(array)

        System.setOut(printWriter)

        printExpression(tokens)

        System.setOut(systemOut)

        return array.toString()
    }

}