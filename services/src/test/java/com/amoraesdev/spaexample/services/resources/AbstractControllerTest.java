package com.amoraesdev.spaexample.services.resources;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.amoraesdev.spaexample.services.RestErrorsControllerAdvice;

public class AbstractControllerTest {

	protected ExceptionHandlerExceptionResolver createExceptionResolver() {
	    ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
	        protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
	            Method method = new ExceptionHandlerMethodResolver(RestErrorsControllerAdvice.class).resolveMethod(exception);
	            return new ServletInvocableHandlerMethod(new RestErrorsControllerAdvice(), method);
	        }
	    };
	    exceptionResolver.afterPropertiesSet();
	    return exceptionResolver;
	}
	
}
