package com.springweb.sample.calculate.service;

import com.springweb.sample.calculate.service.enums.OperationEnumType;
import com.springweb.sample.calculate.util.CommonUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class InfixToPostfixConverterService {

    /**
     * Determines the priority ranking of an operator.
     *
     * @param operator The operator to evaluate.
     * @return An integer representing the priority ranking of the operator.
     */

    public int exprRank(String operator) {
        int ranking = 0;

        // Assign priority levels based on the operator type.
        // 연산자 유형에 따라 우선순위 수준을 할당.
        if (isLowPriorityOperator(operator)) {
            // Addition and subtraction have lower priority.
            // 덧셈과 뺄셈은 낮은 우선순위.
            ranking = 1;
        } else if (isHighPriorityOperator(operator)) {
            // Multiplication and division have higher priority.
            // 곱셈과 나눗셈은 높은 우선순위.
            ranking = 2;
        }
        return ranking;
    }

    /**
     * takeing a token and adding it to the expression stack or adds an operator to the postfix expression list.
     * 토큰을 받아와 표현식 스택에 추가하거나 후위 표현식 목록에 연산자를 추가.
     * @param exprStack      expression stack(표현식 스택)
     * @param postOrderList postfix expression list (후위 표현식 리스트)
     */
    public void exprAppend(String token, Stack<String> exprStack, List<Object> postOrderList) {
        if (CommonUtils.PRIORITY_OPERATORS[0].equals(token)) {
            // If the token is  "(", add it to the exprStack.
            // 토큰이 "(" 인 경우, 표현식 스택에 추가.
            exprStack.push(token);
        } else if (CommonUtils.PRIORITY_OPERATORS[1].equals(token)) {
            // If the token is a ")", pop operators from the exprStack and add them to the postOrderList
            // until an opening parenthesis "(" is encountered.
            // 토큰이 ")" 인 경우, "(" 가 나올 때까지 exprStack에서 연산자를 pop,postOrderList에 추가.
            while (!exprStack.isEmpty()) {
                String opcode = exprStack.pop();
                if (CommonUtils.PRIORITY_OPERATORS[0].equals(opcode)) {
                    // Stop the loop when an  "(" is encountered.
                    //  "(" 를 만나면 반복을 종료.
                    break;
                }
                postOrderList.add(opcode);
            }
        } else if (CommonUtils.containsWord(token,CommonUtils.ARITHMETIC_OPERATORS)) {
            // 토큰이 연산자인 경우, exprStack에 연산자를 추가.
            // If the token is an operator, add it to the exprStack.
            while (true) {
                if (exprStack.isEmpty()) {
                    // 스택이 비어 있으면 토큰을 exprStack에 추가.
                    // If the stack is empty,  add the token to the exprStack.
                    exprStack.push(token);
                    break;
                }
                String opcode = exprStack.pop();
                if (exprRank(opcode) <= exprRank(token)) {
                    // 스택에서 팝한 연산자의 우선순위가 토큰보다 작거나 같으면 스택에 연산자를 추가, 토큰을 스택에 추가.
                    // If the priority of the popped operator is less than or equal to the priority of the token,
                    // add the operator to the stack and then add the token to the stack.
                    exprStack.push(opcode);
                    exprStack.push(token);
                    break;
                }
                // 팝한 연산자를 후위 표현식 목록에 추가합니다.
                // Add the popped operator to the postfix expression list.
                postOrderList.add(opcode);
            }
        }
    }

    /**
     * Converts the infix expression represented by a list of tokens into a postfix (RPN) expression.
     * 토큰 목록을 사용하여 중위 표현식을 후위 표현식(RPN)으로 변환.
     *
     * @param tokenList for infix expression. 중위 표현식 토큰 목록
     * @return tokenList for postfix expression. 후위 표현식 토큰 목록
     */
    public List<Object> convertPostOrder(List<Object> tokenList) {
        List<Object> postOrderList = new ArrayList<>();
        Stack<String> exprStack = new Stack<>();

        for (Object token : tokenList) {
            if (token instanceof BigDecimal) {
                // If the token is a number, add it to the postfix expression.
                // 토큰이 숫자인 경우, 후위 표현식에 추가.
                postOrderList.add(token);
            } else {
                // If the token is an operator or parenthesis, process it using exprAppend.
                // 토큰이 연산자 또는 괄호인 경우, exprAppend를 사용하여 처리.
                exprAppend((String) token, exprStack, postOrderList);
            }
        }

        // Pop any remaining operators from the expression stack and add them to the postfix expression.
        // 남아 있는 연산자를 표현식 스택에서 뺴서 후위 표현식에 추가합니다.
        while (!exprStack.isEmpty()) {
            postOrderList.add(exprStack.pop());
        }

        return postOrderList;
    }

    /**
     * Checks if the operator is a low-priority operator.
     *
     * @param operator The operator to check.
     * @return True if the operator is low-priority, false otherwise.
     */
    private boolean isLowPriorityOperator(String operator) {
        return OperationEnumType.SUBTRACT.getOperation().equals(operator)
                || OperationEnumType.ADD.getOperation().equals(operator);
    }

    /**
     * Checks if the operator is a high-priority operator.
     *
     * @param operator The operator to check.
     * @return True if the operator is high-priority, false otherwise.
     */
    private boolean isHighPriorityOperator(String operator) {
        return OperationEnumType.MULTIPLY.getOperation().equals(operator)
                || OperationEnumType.DIVIDE.getOperation().equals(operator);
    }
}
