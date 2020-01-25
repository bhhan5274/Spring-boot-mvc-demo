package com.bhhan.springbootmvc.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hbh5274@gmail.com on 2020-01-23
 * Github : http://github.com/bhhan5274
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RequestMapping(value = "/hello2", method = RequestMethod.GET)
public @interface GetHelloMapping {
}
