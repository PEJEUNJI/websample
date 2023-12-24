package com.springweb.sample.calculate.service;

import com.springweb.sample.calculate.service.factory.OperationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

@RequiredArgsConstructor
@Service
public class PostfixCalculatorService {

    private final OperationFactory operationFactory;
    private Integer num = 0;
    /**
     * Calculate the result of a postfix expression using two stacks.
     * 두 개의 스택을 이용해서 후위 표기식의 결과를 계산.
     *
     * @param calcStack The stack for operands and operators., 피연산자와 연산자가 있는 스택
     * @param orderStack The stack to track the order of operations., 연산 순서를 추적하는 스택
     * @param orderList  The list to store the order and results of operations., 연산순서와 결과를 저장하는 list
     * @return processing the expression, 표현식을 처리한 결과
     */
    public Stack<Object> calculatePostfixExpression(Stack<Object> calcStack,
                                                     Stack<Object> orderStack,
                                                     ArrayList<String> orderList) {
        // Check if the last element of calcStack is a BigDecimal
        // calcStack의 마지막 요소가 BigDecimal인지 확인
        if (calcStack.lastElement().getClass().equals(BigDecimal.class)) {
            num++;
            return calcStack;
        }

        BigDecimal op1 = null;
        BigDecimal op2 = null;
        String opcode = null;

        // Check if there are at least 2 elements in calcStack
        // calcStack에 적어도 2개의 요소가 있는지 확인합니다.
        if (calcStack.size() >= 2) {
            // Pop the operator from calcStack
            // calcStack에서 연산자 pop
            opcode = (String) calcStack.pop();

            // Pop the second operand, 두번째 피연산자를 pop
            op2 = (BigDecimal) calcStack.pop();

            // Pop the first operand, 첫쨰 피연산자를 pop
            op1 = (BigDecimal) calcStack.pop();

            // Calculate the result, 결과를 계산
            BigDecimal result = calculateByOpCode(op1, op2, opcode);
            calcStack.push(result);

            // Handle orderStack and orderList, orderStack 및 orderList 처리
            if (num > 1) {
                orderStack.push("(" + op1 + opcode + op2 + ")");
            } else if (num == 1) {
                orderStack.push("(" + orderStack.pop() + opcode + op2 + ")");
            } else {
                String opStr1 = (String) orderStack.pop();
                String opStr2 = (String) orderStack.pop();
                orderStack.push("(" + opStr2 + opcode + opStr1 + ")");
            }
            orderList.add(orderStack.peek() + "=" + result);
            num = 0;
        }
        return calcStack;
    }
    /**
     * Calculate the result, 결과를 계산
     * @return calculateByOpCode
     */
    private BigDecimal calculateByOpCode(BigDecimal op1, BigDecimal op2, String opType) {
        return operationFactory.getOperationService(opType).performOperation(op1, op2);
    }

}
