package com.htp.ex.mapper;

import com.htp.ex.model.Comment;
import com.htp.ex.model.Message;
import com.htp.ex.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {

        Comment comment = new Comment();

        comment.setId(resultSet.getInt("id"));
        comment.setText(resultSet.getString("text"));

//        Message message = new Message();
//
//        message.setId(resultSet.getInt("mes_id"));
//        message.setText(resultSet.getString("mes_text"));
//        message.setLocalDateTime(resultSet.getTimestamp("mes_date_time").toLocalDateTime());
//        message.setLink(resultSet.getString("mes_link"));
//        message.setLinkTitle(resultSet.getString("mes_link_title"));
//        message.setLinkDescription(resultSet.getString("mes_link_description"));
//        message.setLinkCover(resultSet.getString("mes_link_cover"));
//
//        User messageAuthor = new User();
//
//        messageAuthor.setId(resultSet.getString("mes_u_id"));
//        messageAuthor.setName(resultSet.getString("mes_u_name"));
//        messageAuthor.setUserPic(resultSet.getString("mes_u_user_pic"));
//        messageAuthor.setEmail(resultSet.getString("mes_u_email"));
//        messageAuthor.setGender(resultSet.getString("mes_u_gender"));
//        messageAuthor.setLocale(resultSet.getString("mes_u_locale"));
//        messageAuthor.setLastVisit(resultSet.getTimestamp("mes_u_last_visit").toLocalDateTime());
//
//        message.setAuthor(messageAuthor);
//
        User commentAuthor = new User();

        commentAuthor.setId(resultSet.getString("com_u_id"));
//        commentAuthor.setName(resultSet.getString("com_u_id"));
//        commentAuthor.setUserPic(resultSet.getString("com_u_id"));
//        commentAuthor.setEmail(resultSet.getString("com_u_id"));
//        commentAuthor.setGender(resultSet.getString("com_u_id"));
//        commentAuthor.setLocale(resultSet.getString("com_u_id"));
//        commentAuthor.setLastVisit(resultSet.getTimestamp("com_u_id").toLocalDateTime());
//
        comment.setAuthor(commentAuthor);
//        comment.setMessage(message);

        return comment;
    }
}
