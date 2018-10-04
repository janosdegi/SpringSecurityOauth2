package com.oauth2.security;

import com.oauth2.dao.IUserDao;
import com.oauth2.domain.Privilege;
import com.oauth2.domain.Role;
import com.oauth2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dégi János on 2017.09.24..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByField(User.class, "username", username);

        System.out.println("UserDetailsServiceImpl.loadUserByUsername ");
        System.out.println(user.getUsername());


        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("user.getUsername(): " + user.getUsername());
        System.out.println("user.getPassword(): " + user.getPassword());
        System.out.println("user.isEnabled(): " + user.isEnabled());
        for (GrantedAuthority authority : getAuthorities(user.getRoles())) {
            System.out.println("authority.getAuthority(): " + authority.getAuthority());

        }
        System.out.println("-----------------------------------------------------------------------");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(), true,
                true, true, getAuthorities(user.getRoles()));
    }

    /**
     *
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {

        System.out.println("------------  1  --------------");

        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     *
     * @param privileges
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {

        System.out.println("------------  3  --------------");

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String privilege : privileges) {
            System.out.println("----------privilege---------");
            System.out.println(privilege);
            System.out.println("----------------------------");
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    /**
     *
     * @param roles
     * @return
     */
    private List<String> getPrivileges(Collection<Role> roles) {

        System.out.println("------------  2  --------------");

        List<String> privileges = new ArrayList<String>();
        List<Privilege> collection = new ArrayList<Privilege>();
        for (Role role : roles) {
            System.out.println("----------role---------");
            System.out.println(role.getName());
            System.out.println("----------------------------");
            collection.addAll(role.getPrivileges());
        }
        for (Privilege privilege : collection) {
            privileges.add(privilege.getName());
        }
        return privileges;
    }


}
