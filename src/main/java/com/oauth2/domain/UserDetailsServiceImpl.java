//package com.oauth2.domain;
//
//import com.oauth2.dao.IUserDao;
//import com.oauth2.domain.Role;
//import com.oauth2.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by Dégi János on 2017.09.24..
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private static final String ROLE_USER = "ROLE_USER";
//
//    @Autowired
//    private IUserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userDao.findOne(userName);
//
//        System.out.println("UserDetailsServiceImpl.loadUserByUsername ");
//
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found with username: " + userName);
//        }
//
//        System.out.println("-----------------------------------------------------------------------");
//        System.out.println("user.getUsername(): " + user.getUsername());
//        System.out.println("user.getPassword(): " + user.getPassword());
//        System.out.println("user.isEnabled(): " + user.isEnabled());
//        for (GrantedAuthority authority : getAuthorities(ROLE_USER)) {
//            System.out.println("authority.getAuthority(): " + authority.getAuthority());
//
//        }
//        System.out.println("-----------------------------------------------------------------------");
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), user.isEnabled(), true,
//                true, true, getAuthorities(ROLE_USER));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return Arrays.asList(new SimpleGrantedAuthority(role));
//    }
//
//
//
//    //-------------------------------------------------------------------------------
//
////    private Collection<? extends GrantedAuthority> getAuthorities(
////            Collection<Role> roles) {
////
////        return getGrantedAuthorities(getPrivileges(roles));
////    }
////
////    private List<String> getPrivileges(Collection<Role> roles) {
////
////        List<String> privileges = new ArrayList<String>();
////        List<Privilege> collection = new ArrayList<Privilege>();
////        for (Role role : roles) {
////            collection.addAll(role.getPrivileges());
////        }
////        for (Privilege item : collection) {
////            privileges.add(item.getName());
////        }
////        return privileges;
////    }
////
////    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
////        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////        for (String privilege : privileges) {
////            authorities.add(new SimpleGrantedAuthority(privilege));
////        }
////        return authorities;
////    }
//}
