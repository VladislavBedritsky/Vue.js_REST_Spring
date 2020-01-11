package com.htp.ex.dao;

import com.htp.ex.dao.impl.MessageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoProvider {

    @Autowired
    private MessageDao messageDao = new MessageDaoImpl();

    public MessageDao getMessageDao() {
        return messageDao;
    }
}
