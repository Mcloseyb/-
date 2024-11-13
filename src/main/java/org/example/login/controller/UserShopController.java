package org.example.login.controller;

import org.example.login.entity.UserShop;
import org.example.login.entity.UserMaterials;
import org.example.login.service.UserShopService;
import org.example.login.service.UserMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserShopController {

    @Autowired
    private UserShopService userShopService;

    @Autowired
    private UserMaterialsService userMaterialsService;

    @GetMapping("/userShop")
    public String getUserShop(@RequestParam String username, Model model) {
        UserShop userShop = userShopService.getUserShop(username);
        UserMaterials userMaterials = userMaterialsService.getUserMaterials(username);
        model.addAttribute("username", username);
        model.addAttribute("userShop", userShop);
        model.addAttribute("userMaterials", userMaterials);
        return "userShop";
    }

    @PostMapping("/purchase")
    @ResponseBody
    public Map<String, Object> purchase(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String materialType = request.get("materialType");
        String ticketType = request.get("ticketType");
        int ticketQuantity = Integer.parseInt(request.get("ticketQuantity"));
        int resultQuantity = Integer.parseInt(request.get("resultQuantity"));

        boolean success = userShopService.purchaseMaterial(username, materialType, ticketType, ticketQuantity, resultQuantity);
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        if (!success) {
            response.put("message", "购买失败，请检查库存和材料数量。");
        }
        return response;
    }
}
