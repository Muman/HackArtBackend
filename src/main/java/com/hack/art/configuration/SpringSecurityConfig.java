package com.hack.art.configuration;

import com.hack.art.security.UrlAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ROLO on 06.11.2015.
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = "com.hack.art")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlAuthenticationHandler handler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login*").anonymous()
                .antMatchers("/rest/admin").hasRole("ADMIN")
                .antMatchers("/rest/register").permitAll()
                .antMatchers("/rest/register/**").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/rest/messages/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.html").permitAll()
                .successHandler(handler).failureUrl("/error")
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
