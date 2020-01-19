package com.htp.ex.service;

import com.htp.ex.model.Comment;
import com.htp.ex.model.User;

import java.util.List;

public interface CommentService {

    void save(Comment comment, User user);

    Comment findLastComment();

    List<Comment> findCommentByMessageId(Integer id);
}
