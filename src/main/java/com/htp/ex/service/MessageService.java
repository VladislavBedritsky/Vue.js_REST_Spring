package com.htp.ex.service;

import com.htp.ex.model.Message;
import com.htp.ex.model.User;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    Message findMessageById (Integer id);

    void save (Message message, User user);

    void update (Message message);

    void delete (Integer id);

    Message findLastMessageInTable ();

    List<Message> findAllSortedByTime();

    public List<Message> findMesAndAuth();

    List<Message> getAllMessagesWithSetComments();
}
