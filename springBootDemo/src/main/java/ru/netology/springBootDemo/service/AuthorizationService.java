package ru.netology.springBootDemo.service;

import ru.netology.springBootDemo.model.UserRequest;
import ru.netology.springBootDemo.util.Authorities;
import ru.netology.springBootDemo.repository.UserRepository;
import ru.netology.springBootDemo.exception.InvalidCredentials;
import ru.netology.springBootDemo.exception.UnauthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public List<Authorities> getAuthorities(UserRequest userRequest) {
        String user = userRequest.getUser();
        String password = userRequest.getPassword();

        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }

        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}