package com.opendragonhuang.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortalController {

    @GetMapping({"/login"})
    public String login(){
        return "login";
    }

    @PostMapping({"/login"})
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        Subject currentSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentSubject.login(token);
        return "info";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String AuthenticationExceptionHandler(AuthenticationException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "login";
    }
}
