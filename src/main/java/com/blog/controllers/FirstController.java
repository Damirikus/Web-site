package com.blog.controllers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    @GetMapping("/")
    public String startPage(Model model) {
        model.addAttribute("title", "My blog");
        return "startPage";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about us");
        return "about";
    }

}
