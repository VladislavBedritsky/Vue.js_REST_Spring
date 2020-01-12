package com.htp.ex.mapper;

import com.htp.ex.model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {

        Message message = new Message();

        message.setId(resultSet.getInt("id"));
        message.setText(resultSet.getString("text"));
        message.setLocalDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());

        return message;
    }
}
