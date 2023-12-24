package com.springweb.sample.calculate.service.operations;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdditionService implements OperationService {

	@Override
	public BigDecimal performOperation(BigDecimal op1, BigDecimal op2) {
		// TODO Auto-generated method stub
		return op1.add(op2);
	}
}
