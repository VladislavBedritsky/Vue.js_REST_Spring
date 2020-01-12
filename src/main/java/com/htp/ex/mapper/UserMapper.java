package com.htp.ex.mapper;

import com.htp.ex.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setUserPic(resultSet.getString("user_pic"));
        user.setEmail(resultSet.getString("email"));
        user.setGender(resultSet.getString("gender"));
        user.setLocale(resultSet.getString("locale"));
        user.setLastVisit(resultSet.getTimestamp("last_visit").toLocalDateTime());

        return user;
    }
}
