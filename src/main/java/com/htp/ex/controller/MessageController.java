package com.htp.ex.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.htp.ex.model.Message;
import com.htp.ex.rest.Views;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

        message.setLocalDateTime(LocalDateTime.now());
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
}
