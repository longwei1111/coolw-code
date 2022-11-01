package com.coolw.code.thread.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/12 10:00
 * @since 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/test1")
    public String test1() {
        return "ok";
    }
    
}
