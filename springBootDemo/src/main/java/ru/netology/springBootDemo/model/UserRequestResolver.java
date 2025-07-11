package ru.netology.springBootDemo.model;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

public class UserRequestResolver implements HandlerMethodArgumentResolver {

    private final Validator validator;

    public UserRequestResolver(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Valid.class) && parameter.getParameterType().equals(UserRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        // Получаем HttpServletRequest из webRequest
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // Получаем параметры из URL
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        UserRequest userRequest = new UserRequest();
        userRequest.setUser(user);
        userRequest.setPassword(password);

        // Валидируем
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(userRequest, "userRequest");
        SpringValidatorAdapter validatorAdapter = new SpringValidatorAdapter(validator);
        validatorAdapter.validate(userRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new org.springframework.web.bind.MethodArgumentNotValidException(parameter, bindingResult);
        }
        return userRequest;
    }
}