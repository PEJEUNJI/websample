package com.springweb.sample.calculate.service.factory;

import com.springweb.sample.calculate.service.enums.OperationEnumType;
import com.springweb.sample.calculate.service.operations.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Returns the operation service for the given operation type.
 */

@Service
@RequiredArgsConstructor
public class OperationFactory {

	private final Map<String, OperationService> operationServices;
	public OperationService getOperationService(String operation) {
		OperationService operationService = null;

		for (OperationEnumType enumType : OperationEnumType.values()) {
			if (enumType.getOperation().equals(operation)) {
				for(String key : operationServices.keySet()){
					if(key.startsWith(enumType.getDescription())){
						operationService = operationServices.get(key);
						break;
					}
				}
			}
		}

		return operationService;
	}
}