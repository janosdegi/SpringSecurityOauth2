package com.oauth2.service;

import com.oauth2.domain.User;

import java.util.List;

/**
 * Created by Dégi János on 2017.09.24..
 */
public interface IUserService {

    public void persist(final User entity);
    public User findOne(final long id);
    public List<User> findAll();
    void createVerificationTokenForUser(final User user, final String token);

}
