package com.springweb.sample.calculate.controller;

import com.springweb.sample.calculate.service.CalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class OperationController {
    private final CalculateService calculateService;

    @GetMapping("/kindcalculate")
    public ArrayList<String> getOperationResult(@RequestParam String problem){
        problem = "(10+1)*(2*2+6)-(20-10)";
        return calculateService.calc(problem);
    }
}
