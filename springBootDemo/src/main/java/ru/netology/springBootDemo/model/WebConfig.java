package ru.netology.springBootDemo.model;

import ru.netology.springBootDemo.model.UserRequestResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Validator validator;

    public WebConfig(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserRequestResolver((javax.validation.Validator) validator));
    }
}