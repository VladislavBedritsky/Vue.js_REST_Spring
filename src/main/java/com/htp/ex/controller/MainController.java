package com.htp.ex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.htp.ex.model.User;
import com.htp.ex.rest.json_view.Views;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.profiles.active}")
    private String profile;

    private ServiceProvider serviceProvider;
    private ObjectWriter writer;

    @Autowired
    public MainController (ServiceProvider serviceProvider, ObjectMapper objectMapper) {
        this.serviceProvider = serviceProvider;

        this.writer = objectMapper
                .setConfig(objectMapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String getIndex (
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {

        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);

        }

        model.addAttribute("messages", writer.writeValueAsString(
                serviceProvider.getMessageService().getAllMessagesWithSetComments()
        ));

        model.addAttribute("data", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
