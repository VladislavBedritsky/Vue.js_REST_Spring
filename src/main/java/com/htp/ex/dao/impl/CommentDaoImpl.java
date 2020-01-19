package com.htp.ex.dao.impl;

import com.htp.ex.dao.CommentDao;
import com.htp.ex.mapper.CommentMapper;
import com.htp.ex.model.Comment;
import com.htp.ex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public void save(Comment comment, User user) {
        String sql = "INSERT INTO comments (id, text, message_id, user_id) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, comment.getId(), comment.getText(),comment.getMessage().getId(), user.getId());
    }

    @Override
    public Comment findLastComment() {
        String sql = "SELECT comments.id as id, comments.text as text, comments.user_id as com_u_id " +
                "FROM comments ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new CommentMapper());
    }

    @Override
    public List<Comment> findCommentByMessageId(Integer id) {
        String sql = "SELECT comments.id as id, comments.text as text, comments.user_id as com_u_id " +
                "FROM comments JOIN messages " +
                "ON comments.message_id = messages.id " +
                "where messages.id = ?" ;
        return jdbcTemplate.query(sql, new CommentMapper() ,id);
    }

}
