package com.dpt.demo.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        try (Connection con = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(
                "SELECT username, password FROM Employee WHERE username='" + username + "'");

            if (!rs.next()) {
                throw new UsernameNotFoundException("User not found");
            }

            return User.withUsername(rs.getString("username"))
                       .password(rs.getString("password")) // TODO hash later
                       .roles("USER")
                       .build();

        } catch (Exception e) {
            throw new UsernameNotFoundException("DB error", e);
        }
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) {
    //     if (!username.equals("testuser")) {
    //         throw new UsernameNotFoundException("User not found");
    //     }

    //     return User.withUsername("testuser")
    //         .password("{noop}testpass")
    //         .roles("USER")
    //         .build();
    // }

}
