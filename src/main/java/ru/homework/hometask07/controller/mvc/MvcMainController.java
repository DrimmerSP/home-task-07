package ru.homework.hometask07.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcMainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
