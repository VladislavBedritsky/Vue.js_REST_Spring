package com.htp.ex.service.impl;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.model.User;
import com.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DaoProvider daoProvider;

    @Override
    public User findById(String id) {
        return daoProvider.getUserDao().findById(id);
    }

    @Override
    public void updateUserLastVisit(String id) {
        daoProvider.getUserDao().updateUserLastVisit(id);
    }

    @Override
    public void save(User user) {
        daoProvider.getUserDao().save(user);
    }

    @Override
    public PrincipalExtractor returnUserFromDbOrCreateNewOne() {

        return map -> {
            String id = (String) map.get("sub");

            User user = daoProvider.getUserDao().findById(id);

            if (user != null) {
                daoProvider.getUserDao().updateUserLastVisit(id);
                return user;
            } else {

                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserPic((String) map.get("picture"));
                newUser.setLastVisit(LocalDateTime.now());
                daoProvider.getUserDao().save(newUser);

                return newUser;
            }
        };
    }
}
