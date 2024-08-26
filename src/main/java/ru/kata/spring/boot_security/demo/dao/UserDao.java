package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findById(Integer id);
}
