package com.example.chapter4.global.resolver;

import com.example.chapter4.global.annotation.CheckPage;
import com.example.chapter4.global.exception.PageValidationException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String pageStr = webRequest.getParameter("page");
        if (pageStr == null) {
            return 0;
        }

        int page = Integer.parseInt(pageStr);
        if (page <= 0) {
            throw new PageValidationException();
        }

        // 프론트는 1-based, JPA는 0-based
        return page - 1;
    }
}
