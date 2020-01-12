package com.htp.ex.dao;

import com.htp.ex.model.User;

import java.time.LocalDateTime;

public interface UserDao {
    User findById(String id);

    void updateUserLastVisit(String id);

    void save (User user);
}
