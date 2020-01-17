package com.htp.ex.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.htp.ex.dto.EventType;
import com.htp.ex.dto.ObjectType;
import com.htp.ex.model.Message;
import com.htp.ex.rest.json_view.Views;
import com.htp.ex.service.ServiceProvider;
import com.htp.ex.util.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {

    private ServiceProvider serviceProvider;
    private BiConsumer<EventType, Message> webSocketSender;

    @Autowired
    public MessageController (ServiceProvider serviceProvider, WebSocketSender webSocketSender) {
        this.serviceProvider = serviceProvider;
        this.webSocketSender = webSocketSender.getSender(ObjectType.MESSAGE, Views.IdAndText.class);
    }

    @GetMapping
    @JsonView(Views.IdAndText.class)
    public List<Message> findAll () {
        return serviceProvider.getMessageService().findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable Integer id) {
        return serviceProvider.getMessageService().findMessageById(id);
    }

    @PostMapping
    public Message create (@RequestBody Message message) throws IOException {
        serviceProvider.getMetaDtoService().fillMeta(message);

        serviceProvider.getMessageService().save(message);
        Message updatedMessage = serviceProvider.getMessageService().findLastMessageInTable();

        webSocketSender.accept(EventType.CREATE, updatedMessage);

        return updatedMessage ;
    }

    @PutMapping("/{id}")
    public Message update (
            @PathVariable("id") Integer id,
            @RequestBody Message message) throws IOException {

        serviceProvider.getMetaDtoService().fillMeta(message);

        serviceProvider.getMessageService().update(message);
        webSocketSender.accept(EventType.UPDATE, message);

        return message;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id) {
        webSocketSender.accept(EventType.REMOVE, serviceProvider.getMessageService().findMessageById(id));
        serviceProvider.getMessageService().delete(id);
    }


    // read me.md
//    @MessageMapping("/changeMessage")
//    @SendTo("/topic/activity")
//    public Message change(Message message) {
//
//        Message m = serviceProvider.getMessageService().findMessageById(message.getId());
//
//        if (m != null) {
//            serviceProvider.getMessageService().update(message);
//            return message;
//        } else {
//            serviceProvider.getMessageService().save(message);
//            return serviceProvider.getMessageService().findLastMessageInTable();
//        }
//    }
}
