package com.htp.ex.service;

import com.htp.ex.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    public MessageService getMessageService() {
        return messageService;
    }
}
