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
    public UserDao(){
        this.connectionMaker = new DConnectionMaker();
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("4");
        user.setName("kyeongrok");
        user.setPassword("12345678");

//        User user2 = new User();
//        user2.setId("2");
//        user2.setName("kyeongrok");
//        user2.setPassword("1234");

//        dao.add(user);
//        dao.add(user2);

//        System.out.println(user.getId() + " 등록 성공");


        User user3 = dao.get(user.getId());
        System.out.println(user3.getName());
        System.out.println(user3.getPassword());
        System.out.println(user3.getId() + " 조회 성공");
    }
}
