package com.htp.ex.service;

import com.htp.ex.service.impl.MessageServiceImpl;
import com.htp.ex.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    @Autowired
    private UserService userService = new UserServiceImpl();

    public MessageService getMessageService() {
        return messageService;
    }

    public UserService getUserService() {
        return userService;
    }
}
