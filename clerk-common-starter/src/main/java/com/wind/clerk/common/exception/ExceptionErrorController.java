package com.wind.clerk.common.exception;

import com.wind.clerk.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class ExceptionErrorController implements ErrorController, Ordered {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Value("${server.error.includeStackTrace:false}")
    private Boolean includeStackTrace;
    /**
     * 默认错误
     */
    private static final String path_default = "/error";

    @Override
    public String getErrorPath() {
        return path_default;
    }

    /**
     * JSON格式错误信息
     */
    @RequestMapping(value = path_default, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> body = this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
        return ApiResponse.builder().code((Integer) body.get("status")).message((String) body.get("message")).data(body.get("errors")).build();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}