package com.htp.ex.dao;

import com.htp.ex.model.Message;
import com.htp.ex.model.User;

import java.util.List;

public interface MessageDao {

    List<Message> findAll();

    Message findMessageById (Integer id);

    void update (Message message);

    void save (Message message, User user);

    void delete (Integer id);

    Message findLastMessageInTable ();

    List<Message> findAllSortedByTime();

    List<Message> findMesAndAuth();
}
