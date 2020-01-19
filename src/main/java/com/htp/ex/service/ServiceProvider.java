package com.htp.ex.service;

import com.htp.ex.service.impl.CommentServiceImpl;
import com.htp.ex.service.impl.MessageServiceImpl;
import com.htp.ex.service.impl.MetaDtoServiceImpl;
import com.htp.ex.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Autowired
    private MessageService messageService = new MessageServiceImpl();

    @Autowired
    private UserService userService = new UserServiceImpl();

    @Autowired
    private MetaDtoService metaDtoService = new MetaDtoServiceImpl();

    @Autowired
    private CommentService commentService = new CommentServiceImpl();

    public MessageService getMessageService() {
        return messageService;
    }

    public UserService getUserService() {
        return userService;
    }

    public MetaDtoService getMetaDtoService() {
        return metaDtoService;
    }

    public CommentService getCommentService() {
        return commentService;
    }
}
