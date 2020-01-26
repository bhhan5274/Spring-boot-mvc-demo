package com.bhhan.springbootmvc.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by hbh5274@gmail.com on 2020-01-26
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping("/api/events")
public class EventApi {
    @PostMapping
    public Event createEvent(HttpEntity<Event> request){

        final MediaType contentType = request.getHeaders().getContentType();

        return request.getBody();
    }

    @PostMapping("/validation")
    public ResponseEntity<Event> createEvent_validation(@RequestBody @Valid Event event, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors()
                    .forEach(System.out::println);

            return ResponseEntity.badRequest().build();
        }

        final ResponseEntity<Event> responseEntity = ResponseEntity.ok(event);

        return responseEntity;
    }
}
