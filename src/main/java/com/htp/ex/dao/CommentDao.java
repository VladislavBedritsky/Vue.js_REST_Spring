package com.htp.ex.dao;

import com.htp.ex.model.Comment;
import com.htp.ex.model.User;

import java.util.List;

public interface CommentDao {

    List<Comment> findAll ();

    void save(Comment comment, User user);

    Comment findLastComment();

    List<Comment> findCommentByMessageId(Integer id);
}
