package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public  class UserDao {
//    SimpleConnectionMaker connectionMaker = new SimpleConnectionMaker();

    private  ConnectionMaker connectionMaker;
//    public  Connection getConnection() throws ClassNotFoundException, SQLException;
////    {
////        Class.forName("com.mysql.cj.jdbc.Driver");
////
////        Map<String, String> env = getenv();
////        String dbHost = env.get("DB_HOST");
////        String dbUser = env.get("DB_USER");
////        String dbPassword = env.get("DB_PASSWORD");
////        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
////
////        return conn;
////    }
    public UserDao(ConnectionMaker cm){
        this.connectionMaker = cm;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        Map<String, String> env = getenv();
//        String dbHost = env.get("DB_HOST");
//        String dbUser = env.get("DB_USER");
//        String dbPassword = env.get("DB_PASSWORD");
//        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

//        Connection conn = connectionMaker.makeNewConnection();
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(ID, NAME, PASSWORD) VALUES (?, ?, ?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
//        Connection conn = connectionMaker.makeNewConnection();

        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, password FROM users WHERE id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }


}
