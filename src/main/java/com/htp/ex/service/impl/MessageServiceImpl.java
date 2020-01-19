package com.htp.ex.service.impl;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.model.Message;
import com.htp.ex.model.User;
import com.htp.ex.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private DaoProvider daoProvider;

    @Override
    public List<Message> findAll() {
        return daoProvider.getMessageDao().findAll();
    }

    @Override
    public Message findMessageById(Integer id) {
        return daoProvider.getMessageDao().findMessageById(id);
    }

    @Override
    public void save(Message message, User user) {
        daoProvider.getMessageDao().save(message, user);
    }

    @Override
    public void update(Message message) {
        daoProvider.getMessageDao().update(message);
    }

    @Override
    public void delete(Integer id) {
        daoProvider.getMessageDao().delete(id);
    }

    @Override
    public Message findLastMessageInTable() {
        return daoProvider.getMessageDao().findLastMessageInTable();
    }

    @Override
    public List<Message> findAllSortedByTime() {
        return daoProvider.getMessageDao().findAllSortedByTime();
    }

    @Override
    public List<Message> findMesAndAuth() {
        return daoProvider.getMessageDao().findMesAndAuth();
    }

    @Override
    public List<Message> getAllMessagesWithSetComments() {
        List<Message> messages = findAll();
        for(Message temp : messages) {
            temp.setComments(daoProvider.getCommentDao().findCommentByMessageId(temp.getId()));
        }

        return messages;
    }
}
