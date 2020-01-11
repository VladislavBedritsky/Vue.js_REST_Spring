package com.htp.ex.dao;

import com.htp.ex.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> findAll();

    Message findMessageById (Integer id);

    void update (Message message);

    void save (Message message);

    void delete (Integer id);

    Message findLastMessageInTable ();

}
