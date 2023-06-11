package com.diploma.skillmaster.controller;

import com.diploma.skillmaster.dto.UserDto;
import com.diploma.skillmaster.service.RoleService;
import com.diploma.skillmaster.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for handling authentication and user registration.
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;

    /**
     * Retrieves the home page.
     *
     * @return The view name for the home page.
     */
    @GetMapping("/")
    public String getHomePage(HttpSession session) {
        userService.putProfileNameInSession(session);
        return "home";
    }

    /**
     * Retrieves the login page.
     *
     * @return The view name for the login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Retrieves the registration form.
     *
     * @param model The Model object to add attributes for the view.
     * @return The view name for the registration page.
     */
    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * Registers a new user.
     *
     * @param userDto    The UserDto object containing user details.
     * @param result     The BindingResult object for validation errors.
     * @param model      The Model object to add attributes for the view.
     * @return The view name for the home page if registration is successful, or the registration page if there are errors.
     */
    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult result, Model model) {
        if (userService.existsByEmailOrUsername(userDto.getEmail(), userDto.getUsername())) {
            return "redirect:/registration?fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "registration";
        }

        roleService.addUserRoleByDefaultAndSave(userDto);
        return "redirect:/login";
    }
}