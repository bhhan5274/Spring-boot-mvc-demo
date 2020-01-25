package com.bhhan.springbootmvc.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hbh5274@gmail.com on 2020-01-23
 * Github : http://github.com/bhhan5274
 */

@Controller
public class SampleController {

    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String hello(WebRequest webRequest, HttpServletRequest httpServletRequest){

        return "hello";
    }

    @GetHelloMapping
    public ResponseEntity<String> hello2(){
        final ResponseEntity<String> hello2 = ResponseEntity.ok("hello2");

        return hello2;
    }

    @GetMapping("/events")
    @ResponseBody
    public String getEvents(){
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getEventById(@PathVariable Long id){
        return "event " + id;
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE,
     produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postEvents(){
        return "postEvents";
    }

    @DeleteMapping("/events/{id}")
    public String deleteEvent(@PathVariable Long id){
        return "deleteEvent " + id;
    }

    @PutMapping(value = "/events/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String putEvent(@PathVariable Long id){
        return "putEvent" + id;
    }

}
