package com.springweb.sample.calculate.service;

import com.springweb.sample.calculate.service.InfixToPostfixConverterService;
import com.springweb.sample.calculate.service.PostfixCalculatorService;
import com.springweb.sample.calculate.service.TokenProcessorService;
import com.springweb.sample.calculate.service.factory.OperationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
@RequiredArgsConstructor
@Service
public class CalculateService {
    private final OperationFactory operationFactory;
	private final InfixToPostfixConverterService infixToPostfixConverterService;
	private final PostfixCalculatorService postfixCalculatorService;
	private final TokenProcessorService tokenProcessorService;

	private final String[] PRIORITY_OPERATORS  = { "(", ")" };
	// Arithmetic operators, 산술 연산자
	private final String[] ARITHMETIC_OPERATORS  = { "+", "-", "*", "/" };

	/**
	 * 
	 * @param data
	 * @return
	 */
	public ArrayList<String> calc(String data) {
		data = data.replace(" ", "");
		// Convert the data to a list of tokens
		// EX) (10+2)*(3+4) : (, 10, +, 2, ), *, (, 3, +, 4, )
		List<Object> tokenStack = tokenProcessorService.makeTokens(data);
		
		tokenStack = infixToPostfixConverterService.convertPostOrder(tokenStack);
		Stack<Object> calcStack = new Stack<Object>();
		Stack<Object> orderStack = new Stack<Object>();
		ArrayList<String> orderList = new ArrayList<String>();
		
		for (Object token : tokenStack) {
			calcStack.push(token);
			calcStack = postfixCalculatorService.calculatePostfixExpression(calcStack, orderStack, orderList);
		}
		return orderList;
	}

}