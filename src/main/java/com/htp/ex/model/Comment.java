package com.htp.ex.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.htp.ex.rest.json_view.Views;

import java.util.Objects;

public class Comment {

    @JsonView(Views.Id.class)
    private Integer id;

    @JsonView(Views.IdAndText.class)
    private String text;

    private Message message;

    @JsonView(Views.FullMessage.class)
    private User author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
