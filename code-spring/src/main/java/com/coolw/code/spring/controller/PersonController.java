package com.coolw.code.spring.controller;

import com.coolw.code.spring.service.PersonService1;
import com.coolw.code.spring.service.PersonService2;
import com.coolw.code.spring.service.PersonService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author coolw
 * @date 2022/10/13 8:38
 * @since 1.0
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonService1 personService1;
    @Autowired
    private PersonService2 personService2;
    @Autowired
    private PersonService3 personService3;
    
    @GetMapping("/test")
    public String test() {
        personService1.add();
        personService2.add();
        personService3.add();
        return "ok";
    }
}
