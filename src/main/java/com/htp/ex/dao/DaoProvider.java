package com.htp.ex.dao;

import com.htp.ex.dao.impl.CommentDaoImpl;
import com.htp.ex.dao.impl.MessageDaoImpl;
import com.htp.ex.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoProvider {

    @Autowired
    private MessageDao messageDao = new MessageDaoImpl();

    @Autowired
    private UserDao userDao = new UserDaoImpl();

    @Autowired
    private CommentDao commentDao = new CommentDaoImpl();

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }
}
