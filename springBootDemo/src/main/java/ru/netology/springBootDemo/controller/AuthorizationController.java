package ru.netology.springBootDemo.controller;

import ru.netology.springBootDemo.service.AuthorizationService;
import ru.netology.springBootDemo.util.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.netology.springBootDemo.model.UserRequest;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final AuthorizationService service;

    @Autowired
    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> authorize(@RequestParam("user") String user,
                                       @RequestParam("password") String password) {
        // Создаем объект UserRequest из параметров
        UserRequest userRequest = new UserRequest();
        userRequest.setUser(user);
        userRequest.setPassword(password);

        // Вызываем сервис
        return service.getAuthorities(userRequest);
    }
}