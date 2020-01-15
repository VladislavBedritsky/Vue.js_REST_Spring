package com.htp.ex.dao.impl;

import com.htp.ex.dao.MessageDao;
import com.htp.ex.mapper.MessageMapper;
import com.htp.ex.mapper.UserMapper;
import com.htp.ex.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        try {
            return jdbcTemplate.queryForObject(sql, new MessageMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Message message) {
        String sql = "UPDATE messages SET text =?, date_time =? where id = ?";
        jdbcTemplate.update(sql,message.getText(), LocalDateTime.now(), message.getId());
    }

    @Override
    public void save(Message message) {
        String sql = "INSERT INTO messages (id, text, date_time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, message.getId(), message.getText(), LocalDateTime.now());
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

    @Override
    public List<Message> findAllSortedByTime() {
        String sql = "SELECT * FROM messages ORDER BY date_time DESC";
        return jdbcTemplate.query(sql, new MessageMapper());
    }
}
