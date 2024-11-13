package org.example.login.controller;

import org.example.login.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/getOperatorImage")
    @ResponseBody
    public Map<String, Object> getOperatorImage(@RequestParam("operatorName") String operatorName) {
        return operatorService.getOperatorImage(operatorName);
    }
}
