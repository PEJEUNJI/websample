package com.springweb.sample.calculate.service;

import com.springweb.sample.calculate.util.CommonUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TokenProcessorService {
    public List<Object> makeTokens(String inputData) {
        List<Object> tokenStack = new ArrayList<>();
        StringBuilder numberTokenBuffer = new StringBuilder();
        char token;

        for (int i = 0; i < inputData.length(); i++) {
            token = inputData.charAt(i);

            if (isNumberCharacter(token)) {
                numberTokenBuffer.append(token);

                // If it's the last character, add the number token.
                // 마지막 문자인 경우 숫자 토큰을 추가.
                if (i == inputData.length() - 1) {
                    BigDecimal number = new BigDecimal(numberTokenBuffer.toString());
                    tokenStack.add(number);
                }
            } else if (CommonUtils.containsWord(Character.toString(token), CommonUtils.PRIORITY_OPERATORS)
                    || CommonUtils.containsWord(Character.toString(token), CommonUtils.ARITHMETIC_OPERATORS)) {
                // Add the number token if it exists.
                // 숫자 토큰이 있는 경우 추가.
                if (!numberTokenBuffer.isEmpty()) {
                    BigDecimal number = new BigDecimal(numberTokenBuffer.toString());
                    tokenStack.add(number);
                    numberTokenBuffer.setLength(0);
                }

                // Add the operator token.
                // 연산자 토큰을 추가.
                String tokenBuffer = Character.toString(token);
                tokenStack.add(tokenBuffer);
            }
        }

        return tokenStack;
    }
    private boolean isNumberCharacter(char token) {

        return (token >= '0' && token <= '9') || token == '.';
    }
}
