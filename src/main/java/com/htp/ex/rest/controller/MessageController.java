package com.htp.ex.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.htp.ex.model.Message;
import com.htp.ex.rest.json_view.Views;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private ServiceProvider serviceProvider;

    @GetMapping
    @JsonView(Views.IdAndName.class)
    public List<Message> findAll () {
        return serviceProvider.getMessageService().findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.IdAndLocalDate.class)
    public Message getOne(@PathVariable Integer id) {
        return serviceProvider.getMessageService().findMessageById(id);
    }

    @PostMapping
    public Message create (@RequestBody Message message) {

        serviceProvider.getMessageService().save(message);

        return serviceProvider.getMessageService().findLastMessageInTable();
    }

    @PutMapping("/{id}")
    public Message update (
            @PathVariable("id") Integer id,
            @RequestBody Message message) {

        serviceProvider.getMessageService().update(message);

        return message;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceProvider.getMessageService().delete(id);
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message) {

        Message m = serviceProvider.getMessageService().findMessageById(message.getId());

        if (m != null) {
            serviceProvider.getMessageService().update(message);
            return message;
        } else {
            serviceProvider.getMessageService().save(message);
            return serviceProvider.getMessageService().findLastMessageInTable();
        }
    }
}
