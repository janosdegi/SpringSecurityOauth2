package com.oauth2.dao;

import com.oauth2.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Dégi János on 2017.09.23..
 */
@Repository
public class UserDao extends AbstractJpaDao<User> implements IUserDao {

    public UserDao() {
        super();

        setClazz(User.class);
    }

}
