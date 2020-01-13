package com.htp.ex.service;

import com.htp.ex.model.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

public interface UserService {

    User findById(String id);

    void updateUserLastVisit(String id);

    void save (User user);

    PrincipalExtractor returnUserFromDbOrCreateNewOne ();

}
