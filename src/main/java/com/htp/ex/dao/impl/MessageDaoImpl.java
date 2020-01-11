package com.htp.ex.dao.impl;

import com.htp.ex.dao.MessageDao;
import com.htp.ex.mapper.MessageMapper;
import com.htp.ex.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> findAll() {
        String sql="SELECT * FROM messages";
        return jdbcTemplate.query(sql, new MessageMapper());
    }

    @Override
    public Message findMessageById(Integer id) {
        String sql = "SELECT * FROM messages where id=?";
        return jdbcTemplate.queryForObject(sql, new MessageMapper(), id);
    }

    @Override
    public void update(Message message) {
        String sql = "UPDATE messages SET text =? where id = ?";
        jdbcTemplate.update(sql,message.getText(),message.getId());
    }

    @Override
    public void save(Message message) {
        String sql = "INSERT INTO messages (id, text) VALUES (?, ?)";
        jdbcTemplate.update(sql, message.getId(), message.getText());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM messages where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Message findLastMessageInTable () {
        String sql = "SELECT * FROM messages ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new MessageMapper());
    }
}
