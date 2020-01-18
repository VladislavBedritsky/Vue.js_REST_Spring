package com.htp.ex.mapper;

import com.htp.ex.model.Message;
import com.htp.ex.model.User;
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
        message.setLink(resultSet.getString("link"));
        message.setLinkTitle(resultSet.getString("link_title"));
        message.setLinkDescription(resultSet.getString("link_description"));
        message.setLinkCover(resultSet.getString("link_cover"));

        User messageAuthor = new User();

        messageAuthor.setId(resultSet.getString("mes_u_id"));
        messageAuthor.setName(resultSet.getString("mes_u_name"));
        messageAuthor.setUserPic(resultSet.getString("mes_u_user_pic"));
        messageAuthor.setEmail(resultSet.getString("mes_u_email"));
        messageAuthor.setGender(resultSet.getString("mes_u_gender"));
        messageAuthor.setLocale(resultSet.getString("mes_u_locale"));
        messageAuthor.setLastVisit(resultSet.getTimestamp("mes_u_last_visit").toLocalDateTime());

        message.setAuthor(messageAuthor);

        return message;
    }
}
