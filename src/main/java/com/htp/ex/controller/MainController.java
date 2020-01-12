package com.htp.ex.controller;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.model.User;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ServiceProvider serviceProvider;

    @GetMapping
    public String getIndex (
            Model model,
            @AuthenticationPrincipal User user
    ) {

        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", serviceProvider.getMessageService().findAll());

        model.addAttribute("data", data);

        return "index";
    }
}
