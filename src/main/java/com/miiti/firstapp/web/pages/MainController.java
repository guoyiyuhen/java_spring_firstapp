package com.miiti.firstapp.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/login2"})
    public String entry() {
        return "index";
    }
}
