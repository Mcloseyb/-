package org.example.login.controller;

import org.example.login.entity.UserMaterials;
import org.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserMaterialsController {

    @Autowired
    private UserService userService;

    @GetMapping("/userMaterials")
    public String getUserMaterials(@RequestParam String username, Model model) {
        UserMaterials userMaterials = userService.getUserMaterials(username);
        if (userMaterials != null) {
            model.addAttribute("userMaterials", userMaterials);
        } else {
            model.addAttribute("error", "User not found");
        }
        return "userMaterials";
    }

    @GetMapping("/welcomeMaterials")
    public String welcomeMaterials(@RequestParam String username, Model model) {
        UserMaterials userMaterials = userService.getUserMaterials(username);
        if (userMaterials != null) {
            model.addAttribute("userMaterials", userMaterials);
        }
        model.addAttribute("username", username);
        return "welcome";
    }
}
