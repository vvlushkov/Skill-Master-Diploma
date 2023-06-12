package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.diploma.skillmaster.security.SecurityUtil;

@Controller
@RequestMapping("/creator")
@RequiredArgsConstructor
public class CreatorController {
    private final RoleService roleService;

    @GetMapping("/new")
    public String addRole(@RequestParam(value = "choice", required = false) String choice) {
        if (choice != null) {
            if (choice.equals("ACCEPT")) {
                roleService.addCreatorRole();

                SecurityUtil.updateAuthorities("CREATOR");

                return "redirect:/?creatorYes";
            }
            if (choice.equals("DENY")) {
                return "redirect:/?creatorNo";
            }
        }

        return "creator-form";
    }
}
