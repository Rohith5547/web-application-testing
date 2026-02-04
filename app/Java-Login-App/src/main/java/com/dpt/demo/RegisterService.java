package com.dpt.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void registerUser(
            String firstName,
            String lastName,
            String email,
            String userName,
            String passwordValue
    ) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql =
            "INSERT INTO Employee " +
            "(first_name, last_name, email, username, password, regdate) " +
            "VALUES (?, ?, ?, ?, ?, CURDATE())";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, userName);
            ps.setString(5, passwordValue);

            ps.executeUpdate();
        }
    }
}
