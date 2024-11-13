package org.example.login.controller;

import org.example.login.entity.UserMaterials;
import org.example.login.service.RecruitmentService;
import org.example.login.service.UserMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@Controller
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private UserMaterialsService userMaterialsService;

    @GetMapping("/recruitment")
    public String recruitment(@RequestParam String username, Model model) {
        UserMaterials userMaterials = userMaterialsService.getUserMaterials(username);
        model.addAttribute("username", username);
        model.addAttribute("userMaterials", userMaterials);
        return "recruitment";
    }

    @PostMapping("/startRecruitment")
    public String startRecruitment(@RequestParam("username") String username, @RequestParam("keyword") String keyword, @RequestParam("boxNumber") int boxNumber, Model model) {
        // 扣除公招券和龙门币
        userMaterialsService.deductMaterials(username, 1, 1);

        // 调用存储过程
        Map<String, Object> result = recruitmentService.callRecruitmentProcedure(username, keyword, boxNumber);

        // 将结果添加到模型中
        model.addAttribute("username", username);
        model.addAttribute("operatorName", result.get("operatorName"));

        byte[] operatorImage = (byte[]) result.get("operatorImage");
        if (operatorImage != null) {
            String base64Image = Base64.getEncoder().encodeToString(operatorImage);
            model.addAttribute("operatorImage", base64Image);
        } else {
            model.addAttribute("operatorImage", null);
        }
        return "recruitmentResult";
    }
}
