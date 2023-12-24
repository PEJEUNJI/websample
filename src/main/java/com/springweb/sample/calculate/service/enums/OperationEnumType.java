package com.springweb.sample.calculate.service.enums;
/**
 * An enumeration defining various operation types.
 * 다양한 연산 유형을 정의한 enumeration.
 */
public enum OperationEnumType {

	ADD("+", "addition"),
	SUBTRACT("-", "subtraction"),
	MULTIPLY("*", "multiplication"),
	DIVIDE("/", "division");

	private String operation;
	private String description;

	/**
	 * Initializes the operation type with the provided string.
	 * @param operation 연산 유형 , operation type
	 */

	private OperationEnumType(String operation, String description) {

		this.operation = operation;
		this.description = description;
	}

	/**
	 * Returns the operation type as a string.
	 * @return operation type.
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * Returns the description type as a string.
	 * @return description.
	 */
	public String getDescription() {
		return description;
	}




}
