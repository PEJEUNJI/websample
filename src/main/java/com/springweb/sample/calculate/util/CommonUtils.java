package com.springweb.sample.calculate.util;

import java.util.Arrays;

public class CommonUtils {
    // Priority Operators, 우선순위 연산자
    public static final String[] PRIORITY_OPERATORS  = { "(", ")" };

    // Arithmetic operators, 산술 연산자
    public static final String[] ARITHMETIC_OPERATORS  = { "+", "-", "*", "/" };

    /**
     * Check if a token is contained in the given array of words.
     * 주어진 단어 배열에서 토큰이 포함되어 있는지 확인.
     *
     * @param token The token to be checked.
     * @param check The array of words to search in., 검색할 단어 배열
     * @return true if the token is found in the array; otherwise, false.
     *         토큰이 배열에 포함되면 true, 없으면 false
     */
    public static boolean containsWord(String token, String[] check) {
        return token != null && Arrays.asList(check).contains(token);
    }
}
