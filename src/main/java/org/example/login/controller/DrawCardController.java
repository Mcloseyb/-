package org.example.login.controller;

import org.example.login.entity.UserMaterials;
import org.example.login.service.DrawCardService;
import org.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class DrawCardController {

    @Autowired
    private DrawCardService drawCardService;

    @Autowired
    private UserService userService;

    @GetMapping("/drawCard")
    public String showDrawCardPage(Model model, @RequestParam String username) {
        List<String> sixStarOperators = drawCardService.getSixStarOperators();
        List<String> fiveStarOperators = drawCardService.getFiveStarOperators();
        UserMaterials userMaterials = userService.getUserMaterials(username);

        model.addAttribute("username", username);
        model.addAttribute("sixStarOperators", sixStarOperators);
        model.addAttribute("fiveStarOperators", fiveStarOperators);
        model.addAttribute("userMaterials", userMaterials);

        return "drawCard";
    }

    @PostMapping("/performDrawCard")
    public String drawCard(
            @RequestParam("username") String username,
            @RequestParam("sixStar") String sixStar,
            @RequestParam("fiveStar1") String fiveStar1,
            @RequestParam("fiveStar2") String fiveStar2,
            @RequestParam("drawType") String drawType,
            Model model) {

        if ("single".equals(drawType)) {
            drawCardService.performSingleDraw(username, sixStar, fiveStar1, fiveStar2);
        } else if ("ten".equals(drawType)) {
            drawCardService.performTenDraws(username, sixStar, fiveStar1, fiveStar2);
        }

        List<Map<String, Object>> drawResults;
        if ("single".equals(drawType)) {
            drawResults = drawCardService.getLatestSingleDrawResult(username);
        } else {
            drawResults = drawCardService.getLatestTenDrawResults(username);
        }

        UserMaterials userMaterials = userService.getUserMaterials(username);

        model.addAttribute("username", username);
        model.addAttribute("drawResults", drawResults);
        model.addAttribute("drawType", drawType);
        model.addAttribute("userMaterials", userMaterials);

        return "drawResult"; // 显示抽卡结果页面
    }
}
