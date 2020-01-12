package com.htp.ex.service.impl;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DaoProvider daoProvider;
}
