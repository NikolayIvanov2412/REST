package ru.netology.springBootDemo.repository;

import ru.netology.springBootDemo.util.Authorities;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<Authorities> getUserAuthorities(String user, String password) {
        // Временно делаем простую проверку
        if ("admin".equals(user) && "adminpass".equals(password)) {
            List<Authorities> authorities = new ArrayList<>();
            authorities.add(Authorities.READ);
            authorities.add(Authorities.WRITE);
            authorities.add(Authorities.DELETE);
            return authorities;
        } else if ("user".equals(user) && "userpass".equals(password)) {
            List<Authorities> authorities = new ArrayList<>();
            authorities.add(Authorities.READ);
            return authorities;
        }
        return new ArrayList<>(); // пустой список — пользователь не найден или неверный пароль
    }
}