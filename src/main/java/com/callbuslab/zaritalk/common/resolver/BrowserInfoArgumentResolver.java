package com.callbuslab.zaritalk.common.resolver;

import com.callbuslab.zaritalk.base.anotation.BrowserInfo;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class BrowserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isBrowserInfoAnnotation = parameter.getParameterAnnotation(BrowserInfo.class) != null; //컨트롤러에 파라미터 어노테이션 체크
        boolean isStringType = parameter.getParameterType().equals(String.class); //파라미터에 타입 체크
        return isBrowserInfoAnnotation && isStringType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        return req.getHeader("User-Agent");
    }
}
