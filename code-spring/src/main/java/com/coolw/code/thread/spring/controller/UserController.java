package com.coolw.code.thread.spring.controller;

import com.coolw.code.thread.spring.convert.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/13 17:16
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    
    @PostMapping("/test")
    private String test(@RequestBody User user) {
        log.info("user:{}", user);
        return "ok";
    }
}
