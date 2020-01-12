package com.htp.ex.dao.impl;

import com.htp.ex.dao.UserDao;
import com.htp.ex.mapper.UserMapper;
import com.htp.ex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM users where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateUserLastVisit(String id) {
        String sql = "UPDATE users SET last_visit = ? where id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), id);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (id, name, user_pic, email, gender, locale, last_visit) VALUES " +
                "(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getUserPic(),
                user.getEmail(), user.getGender(), user.getLocale(), user.getLastVisit());
    }
}
