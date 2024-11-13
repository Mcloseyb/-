package org.example.login.controller;

import org.example.login.entity.Operator;
import org.example.login.entity.UserMaterials;
import org.example.login.service.OperatorService;
import org.example.login.service.UserMaterialsService;
import org.example.login.service.UserOperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserOperatorsController {

    @Autowired
    private UserOperatorsService userOperatorsService;

    @Autowired
    private UserMaterialsService userMaterialsService;

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/userOperators")
    public String getUserOperators(@RequestParam String username, Model model) {
        List<Map<String, Object>> userOperators = userOperatorsService.findUserOperatorsByUsername(username)
                .stream()
                .map(operator -> {
                    Map<String, Object> operatorData = operator.toMap();
                    Map<String, Object> operatorImage = operatorService.getOperatorImage(operator.getOperatorName());
                    operatorData.put("operatorImage", operatorImage.get("normalImage"));
                    return operatorData;
                })
                .collect(Collectors.toList());

        model.addAttribute("username", username);
        model.addAttribute("userOperators", userOperators);
        return "userOperators";
    }

    @GetMapping("/operatorLevel")
    public String getOperatorLevel(@RequestParam String username, @RequestParam String operatorName, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("operatorName", operatorName);
        model.addAttribute("operatorLevels", userOperatorsService.findUserOperatorLevelsByUsernameAndOperatorName(username, operatorName));

        UserMaterials userMaterials = userMaterialsService.getUserMaterials(username);
        model.addAttribute("userMaterials", userMaterials);

        Map<String, Object> operatorImage = operatorService.getOperatorImage(operatorName);
        model.addAttribute("normalImage", operatorImage.get("normalImage"));
        model.addAttribute("eliteImage", operatorImage.get("eliteImage"));
        model.addAttribute("operatorToken", userOperatorsService.getOperatorToken(username, operatorName));

        Operator operator = operatorService.getOperatorByName(operatorName);
        model.addAttribute("operator", operator);

        return "operatorLevel";
    }

    @PostMapping("/upgradeLevel")
    @ResponseBody
    public Map<String, Object> upgradeLevel(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String operatorName = request.get("operatorName");
        int newLevel = Integer.parseInt(request.get("level"));

        boolean success = userOperatorsService.upgradeLevel(username, operatorName, newLevel);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "升级失败");
        }
        return response;
    }

    @PostMapping("/increasePotential")
    @ResponseBody
    public Map<String, Object> increasePotential(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String operatorName = request.get("operatorName");

        boolean success = userOperatorsService.increasePotential(username, operatorName);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "提升潜能失败");
        }
        return response;
    }

    @PostMapping("/breakthrough")
    @ResponseBody
    public Map<String, Object> breakthrough(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String operatorName = request.get("operatorName");
        int eliteLevel = Integer.parseInt(request.get("eliteLevel"));

        boolean success = userOperatorsService.breakthrough(username, operatorName, eliteLevel);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "突破失败");
        }
        return response;
    }
}
