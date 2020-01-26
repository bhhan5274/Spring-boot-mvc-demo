package com.bhhan.springbootmvc.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-01-26
 * Github : http://github.com/bhhan5274
 */

@ControllerAdvice(assignableTypes = {HelloController.class, EventApi.class})
public class BaseController {
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException exception, Model model){
        model.addAttribute("message", "runtime error");
        return "error";
    }

    @InitBinder
    public void initEventBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model){
        model.addAttribute("subjects", Arrays.asList("Study", "Seminar"));
    }

    @ModelAttribute("categories")
    public List<String> categories2(){
        return Arrays.asList("Study", "Seminar");
    }
}
