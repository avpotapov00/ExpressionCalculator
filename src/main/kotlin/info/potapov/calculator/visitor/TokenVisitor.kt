package info.potapov.calculator.visitor

import info.potapov.calculator.token.BraceToken
import info.potapov.calculator.token.NumberToken
import info.potapov.calculator.token.OperationToken
import info.potapov.calculator.token.Token

interface TokenVisitor {

    fun visit(numberToken: NumberToken)

    fun visit(braceToken: BraceToken)

    fun visit(operationToken: OperationToken)

}