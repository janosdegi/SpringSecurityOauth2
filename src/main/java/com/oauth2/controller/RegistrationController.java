package com.oauth2.controller;

import com.oauth2.domain.User;
import com.oauth2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class RegistrationController {

    static Logger log = Logger.getLogger(RegistrationController.class.getName());

    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String getRootPath(@ModelAttribute("user")User user, HttpServletRequest request) {
        return "Hello";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody String getUser(@ModelAttribute("user")User user, HttpServletRequest request) {
        return "userList";
    }

    @RequestMapping(value = "/testurl", method = RequestMethod.POST)
    public @ResponseBody String getTestUrl(HttpServletRequest request) {
        return "testurl";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String userRegistration(@ModelAttribute("user")User user, HttpServletRequest request) {

        User u = new User();

        u.setUsername("tdj2@bmi.gv.at");
        u.setPassword("1234");
        u.setEnabled(true);

        System.out.println("username: " + u.getUsername());
        System.out.println("password: " + u.getPassword());

//        User user = new User("tokenuser2","tokenuser2",false);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userService.persist(u);
        final String token = UUID.randomUUID().toString();

        return "user_registration_succeded";
    }


//    todo 1. application/json   -----------------------------------------------------------------------------------

//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST, consumes="application/json")
//  public String userRegistration(@RequestBody User user, HttpServletRequest request) {

//    todo 2. application/x-www-form-urlencoded (@ModelAttribute("user"))  -------------------------------------------

//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    @RequestMapping(value = "/user/registration2", method = RequestMethod.POST)
    public String userRegistration2(User user, HttpServletRequest request) {

    //        User u = new User();
    //
    //        u.setUsername("tdj2@bmi.gv.at");
    //        u.setPassword("1234");
    //        u.setEnabled(true);

        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());

    //        User user = new User("tokenuser2","tokenuser2",false);
    //        u.setPassword(passwordEncoder.encode(u.getPassword()));
    //        userService.persist(u);
    //        final String token = UUID.randomUUID().toString();

        return "user_registration_succeded";
    }

//    1 getting an access_token

//    1.1 set Basic Authentication in Request header: AuthorizationServerConfiguration
//            .withClient("live-test")
//            .secret("A65DG9322K")
//    1.2 http://localhost:8080/oauth/token?grant_type=password&username=user&password=1234

//    2 getting the resource
//    http://localhost:8080/user?access_token=815f3faa-f0f5-4e5d-af0f-a9a972d44ded

}
