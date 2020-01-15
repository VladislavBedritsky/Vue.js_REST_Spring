package com.htp.ex.service;

import com.htp.ex.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    Message findMessageById (Integer id);

    void save (Message message);

    void update (Message message);

    void delete (Integer id);

    Message findLastMessageInTable ();

    List<Message> findAllSortedByTime();
}
