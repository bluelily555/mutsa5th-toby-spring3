package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new DConnectionMaker();
        UserDao dao = new UserDao(cm);

        User user = new User();
        user.setId("8");
        user.setName("kyeongrok");
        user.setPassword("12345678");

        dao.add(user);

        User user3 = dao.get(user.getId());
        System.out.println(user3.getName());
        System.out.println(user3.getPassword());
        System.out.println(user3.getId() + " 조회 성공");
    }
}
