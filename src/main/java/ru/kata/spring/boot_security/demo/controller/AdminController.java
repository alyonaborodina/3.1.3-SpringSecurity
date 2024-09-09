package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    @PostMapping ("/users")
    public ResponseEntity<List<User>> createUser(@Valid @RequestBody User user, @RequestParam("roleIds") Set<String> roleIds) {
        Set<Role> rolesSet = roleService.getRolesFromIds(roleIds);
        user.setRoles(rolesSet);
        userService.save(user);
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<List<User>> updateUser(@PathVariable Integer id, @Valid @RequestBody User user, @RequestParam(value = "selectedRoles") Set<String> selectedRoles ) {
        Set<Role> rolesSet = roleService.getRolesFromIds(selectedRoles);
        user.setRoles(rolesSet);
        user.setId(id);
        userService.save(user);
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping ("/users/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(userService.findAll());
    }

}
