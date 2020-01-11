package com.htp.ex.controller;

import com.htp.ex.model.Message;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private ServiceProvider serviceProvider;

    @GetMapping
    public List<Message> findAll () {
        return serviceProvider.getMessageService().findAll();
    }

    @GetMapping("/{id}")
    public Message getOne(@PathVariable Integer id) {
        return serviceProvider.getMessageService().findMessageById(id);
    }

    @PostMapping
    public Message create (@RequestBody Message message) {

        serviceProvider.getMessageService().save(message);

        System.out.println(serviceProvider.getMessageService().findLastMessageInTable());
        return serviceProvider.getMessageService().findLastMessageInTable();
    }

    @PutMapping("/{id}")
    public Message update (
            @PathVariable("id") Message messageFromDB,
            @RequestBody Message message) {

        BeanUtils.copyProperties(message,messageFromDB, "id");
        serviceProvider.getMessageService().update(messageFromDB);
        System.out.println(message);
        System.out.println(messageFromDB);
        return message;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceProvider.getMessageService().delete(id);
    }
}
