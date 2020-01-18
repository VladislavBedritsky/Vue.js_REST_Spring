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
    public List<Message> findMesAndAuth() {
        String sql = "SELECT messages.id, messages.text, messages.date_time, " +
                "messages.link, messages.link_title, messages.link_description, messages.link_cover, " +
                "users.id AS mes_u_id, users.name AS mes_u_name, " +
                "users.user_pic AS mes_u_user_pic, users.email AS mes_u_email, " +
                "users.gender AS mes_u_gender, users.locale AS mes_u_locale, users.last_visit AS mes_u_last_visit " +
                "FROM messages JOIN users ON messages.user_id = users.id";
        return jdbcTemplate.query(sql, new MessageMapper());
    }

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
        String sql = "UPDATE messages SET text =?, date_time =?, " +
                "link =?, link_title =?, link_description =?, link_cover =? " +
                "where id = ?";
        jdbcTemplate.update(sql,message.getText(), LocalDateTime.now(),
                message.getLink(), message.getLinkTitle(), message.getLinkDescription(), message.getLinkCover(),
                message.getId());
    }

    @Override
    public void save(Message message) {
        String sql = "INSERT INTO messages (id, text, date_time, link, link_title, link_description, link_cover) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, message.getId(), message.getText(), LocalDateTime.now(),
                message.getLink(), message.getLinkTitle(), message.getLinkDescription(), message.getLinkCover());
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
