package org.example.login.controller;

import org.example.login.service.TokenExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TokenExchangeController {

    @Autowired
    private TokenExchangeService tokenExchangeService;

    @GetMapping("/tokenExchange")
    public String tokenExchange(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("yellowTokens", tokenExchangeService.getYellowTokens(username));
        model.addAttribute("greenTokens", tokenExchangeService.getGreenTokens(username));
        return "tokenExchange";
    }

    @GetMapping("/exchangeToken")
    @ResponseBody
    public Map<String, Object> exchangeToken(@RequestParam String username, @RequestParam String type) {
        return tokenExchangeService.exchangeToken(username, type);
    }
}
