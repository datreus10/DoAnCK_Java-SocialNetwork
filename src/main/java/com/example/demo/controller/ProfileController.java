package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    public String getProfile(@RequestParam(name = "id") Long userId, Model model) {

        User guestUser = userService.getUserById(userId);
        User currentUser = userService.getCurrentUser();
        if (guestUser.equals(currentUser)) {
            model.addAttribute("isCurrentUser", true);
        } else {
            model.addAttribute("isCurrentUser", false);
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("guestUser", guestUser);
        model.addAttribute("listPost", postService.getPostByUser(guestUser));
        return "profile";
    }
}