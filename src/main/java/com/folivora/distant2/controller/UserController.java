package com.folivora.distant2.controller;

import com.folivora.distant2.domain.Role;
import com.folivora.distant2.domain.User;
import com.folivora.distant2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    //Ограничение доступа
    @PreAuthorize("hasAuthority('ADMIN')")
    //Вывод списка пользователей
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    //Вывод данных пользователя доступно только администратору
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());


        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    //Изменение данных пользователя доступно только администратору
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("user_id") User user){

       userService.saveUser(user, username, form);

        return "redirect:/user";
    }
    //Вывод данных пользователя доступно только пользователю
    @GetMapping("profile")
    public String getprofile(Model model, @AuthenticationPrincipal User user)
    {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
                return "profile";
    }
    //Изменение данных пользователя доступно только пользователю
    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email

    ){
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

}
