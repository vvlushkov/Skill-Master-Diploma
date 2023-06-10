package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/creator")
@RequiredArgsConstructor
public class CreatorController {
    private final UserService userService;

    @GetMapping("/new")
    public String addRole(@RequestParam("choice") String choice) {

        if (choice.equals("ACCEPT")) {
            userService.addCreatorRole();
            return "redirect:/course?creatorYes";
        }

        if (choice.equals("DENY")) {
            return "redirect:/course?creatorNo";
        }

        return "creator-form";
    }
}
