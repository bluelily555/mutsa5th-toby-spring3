package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;


public class ConnectionChecker {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.check();
        cc.select();
    }

    public void check() throws ClassNotFoundException, SQLException{
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");


        while(rs.next()){
            String line = rs.getString(1);
            System.out.println(line);
        }
//        stmt.execute("create table `Users` ("
//                + "  `id` INT NOT NULL AUTO_INCREMENT "
//                + ",  `name` VARCHAR(45) NOT NULL "
//                + ",  `password` VARCHAR(45) NOT NULL "
//                + ",  PRIMARY KEY (`id`))");

//        stmt.execute("INSERT INTO `Users` (`name`,`password`) VALUES "
//                + "('김미미', 'password')");

//        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//        pstmt.setString(1, "1");
//        pstmt.setString(2, "kyeongrok");
//        pstmt.setString(3, "12345678");
//        pstmt.executeUpdate();

        stmt.execute("UPDATE `Users` "
                + " SET `name` = '김경록'"
                + "   , `password` = 'passpass'"
                + " WHERE `id` = 2"
        );
        rs = stmt.executeQuery("select * from `Users`");


        System.out.println("_++++++++++++++++++++");

        while(rs.next()){
            String id = rs.getString("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            System.out.printf("id: %s, name: %s, password: %s\n", id, name, password);
        }
    }

    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
    }

    public void select() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
    }
}
