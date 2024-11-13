package org.example.login.controller;

import org.example.login.entity.UserMaterials;
import org.example.login.service.UserService;
import org.example.login.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperatorService operatorService; // 添加 OperatorService

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String uname, @RequestParam String psw, Model model) {
        if (userService.authenticate(uname, psw)) {
            return "redirect:/welcome?username=" + uname + "&clearCache=true";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String account, @RequestParam String password, Model model) {
        try {
            userService.registerUser(username, account, password);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("registerError", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam String username, @RequestParam(required = false) String clearCache, Model model) {
        model.addAttribute("username", username);
        UserMaterials userMaterials = userService.getUserMaterials(username);
        if (userMaterials != null) {
            model.addAttribute("userMaterials", userMaterials);
        }

        // 获取用户拥有的干员信息并传递给视图
        model.addAttribute("userOperators", userService.getUserOperatorsWithImages(username));

        // 如果 clearCache 参数存在，传递一个标志给视图以清除本地存储
        if ("true".equals(clearCache)) {
            model.addAttribute("clearCache", true);
        }

        return "welcome";
    }

}
