package com.htp.ex.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.htp.ex.model.Comment;
import com.htp.ex.model.Message;
import com.htp.ex.model.User;
import com.htp.ex.rest.json_view.Views;
import com.htp.ex.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ServiceProvider serviceProvider;

    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Comment create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user
            ) {

        serviceProvider.getCommentService().save(comment,user);
        return serviceProvider.getCommentService().findLastComment();

    }
}
