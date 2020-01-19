package com.htp.ex.service.impl;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.model.Comment;
import com.htp.ex.model.Message;
import com.htp.ex.model.User;
import com.htp.ex.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private DaoProvider daoProvider;

    @Override
    public void save(Comment comment, User user) {
            daoProvider.getCommentDao().save(comment, user);
    }

    @Override
    public Comment findLastComment() {
        return daoProvider.getCommentDao().findLastComment();
    }

    @Override
    public List<Comment> findCommentByMessageId(Integer id) {
        return daoProvider.getCommentDao().findCommentByMessageId(id);
    }
}
