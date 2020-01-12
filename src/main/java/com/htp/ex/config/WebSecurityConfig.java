package com.htp.ex.config;

import com.htp.ex.dao.DaoProvider;
import com.htp.ex.model.User;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor (DaoProvider daoProvider) {
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
