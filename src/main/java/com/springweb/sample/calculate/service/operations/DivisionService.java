package com.springweb.sample.calculate.service.operations;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Service
public class DivisionService implements OperationService {
	// Rounding digits
	private static final int ROUND_UP_SCALE = 6;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	@Override
	public BigDecimal performOperation(BigDecimal op1, BigDecimal op2) {
		if (op1.compareTo(BigDecimal.ZERO) == 0) {
			throw new ArithmeticException("Division by zero is not allowed.");
		}
		// TODO Auto-generated method stub
		return op2.divide(op1, ROUND_UP_SCALE, ROUNDING_MODE);
	}
}
