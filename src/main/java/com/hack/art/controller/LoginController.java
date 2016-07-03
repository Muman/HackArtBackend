package com.hack.art.controller;

import com.hack.art.domain.User;
import com.hack.art.dto.CurrentUser;
import com.hack.art.dto.UserDTO;
import com.hack.art.exception.InternalServerError;
import com.hack.art.service.UserService;
import com.hack.art.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Roman on 25.08.2015.
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LogUtil logger;

    @Autowired
    private UserService userService;

    @Autowired
    private UserData userData;

    @Autowired
    private LogUtil log;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null != auth){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<Properties> getEnMessages(@RequestParam String lang) {
        try {
            Properties properties = new Properties();
            if(lang.equals("en")){
                Locale.setDefault(new Locale("en"));
                LocaleContextHolder.setLocale(new Locale("en"));
                properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_en.properties")));
            } else{
                Locale.setDefault(new Locale("pl"));
                LocaleContextHolder.setLocale(new Locale("pl"));
                properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_pl.properties")));
            }
            return new ResponseEntity<Properties>(properties, HttpStatus.OK);
        } catch (IOException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/messages/pl", method = RequestMethod.GET)
    public ResponseEntity<Properties> getPlMessages() {
        return new ResponseEntity<Properties>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> getLoggedUser(){
        try {
            User user = userService.getUserLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            userData.setCreateDate(user.getCreateDate());
            userData.setRole(user.getRole());
            userData.setLastName(user.getLastName());
            userData.setFirstName(user.getFirstName());
            userData.setEmail(user.getEmail());
            userData.setId(user.getId());
            userData.setLogin(user.getLogin());
            userData.setRoleName(user.getRole().getName().toString());
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        logger.info("getLoginPage");
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody UserDTO user){
        try {
            userService.register(user);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
