package com.callbuslab.zaritalk.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/v1/api/*")
public class ParamFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //----------------전처리----------------
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        //실제 실행
        chain.doFilter(httpServletRequest, httpServletResponse);

        //----------------후처리----------------
        //요청
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        //응답
        int status = httpServletResponse.getStatus();
        String responseContent = new String(httpServletResponse.getContentAsByteArray());

        httpServletResponse.copyBodyToResponse(); //응답을 복사하지 않으면 클라이언트가 보는 응답결과가 사라짐

        log.info("request   url: {}, requestContent: {}", url, reqContent);
        log.info("response  status: {}, responseContent: {}", status, responseContent);
    }
}
