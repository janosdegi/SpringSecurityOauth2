package com.oauth2.service;

import com.oauth2.dao.IUserDao;
import com.oauth2.domain.User;
import com.oauth2.domain.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dégi János on 2017.09.23..
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao dao;

    public UserService() {
        super();
    }

    // API

    public void register(final User entity) {
        final String token = UUID.randomUUID().toString();
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
//        final VerificationToken myToken = new VerificationToken(token, user);
    }

    public void persist(final User entity) {
        dao.persist(entity);
    }

    public User findOne(final long id) {
        return dao.findOne(id);
    }

    public List<User> findAll() {
        return dao.findAll();
    }

}
