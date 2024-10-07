package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    public List<User> getUsers() {
        String sql = "select * from users";
        return jdbc.query(sql, (rs, rowN) -> mapRowToUser(rs));
    }

    public List<User> getUserByName(String name) {
        String sql = "select * from users where name = ?";
        return jdbc.query(sql, new String[]{name}, (rs, rowNum) -> mapRowToUser(rs));
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        jdbc.update(sql, user.getName(), user.getAge(), user.getEmail());
    }


    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
