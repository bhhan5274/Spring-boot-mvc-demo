package com.bhhan.springbootmvc.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-01-25
 * Github : http://github.com/bhhan5274
 */

@Controller
@SessionAttributes("event")
public class HelloController {

    /*@ExceptionHandler({EventException.class, RuntimeException.class})
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
    }*/

    @GetMapping("/goError")
    public String goError(){
        throw new EventException();
    }

    @GetMapping("/event/form/name")
    public String eventFormName(Model model){
        model.addAttribute("event", new Event());
        return "/event/form-name";
    }

    @PostMapping("/event/form/name")
    public String eventFormNameSubmit(@ModelAttribute Event event,
                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/event/form-name";
        }

        return "redirect:/event/form/limit";
    }

    @GetMapping("/event/form/limit")
    public String eventFormLimit(@ModelAttribute Event event, Model model){
        model.addAttribute("limit", event.getLimit());
        return "/event/form-limit";
    }

    @PostMapping("/event/form/limit")
    public String eventFormLimitSubmit(@ModelAttribute Event event,
                                       BindingResult bindingResult,
                                       SessionStatus sessionStatus,
                                       RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            return "/event/form-limit";
        }

        sessionStatus.setComplete();
        attributes.addFlashAttribute("name", event.getName());
        attributes.addFlashAttribute("limit", event.getLimit());
        return "redirect:/event/list";
    }

    @GetMapping("/event/form")
    public String eventForm(Model model, HttpSession httpSession){
        final Event event = new Event();
        event.setLimit(50);
        model.addAttribute("name", event.getName());
        model.addAttribute("limit", event.getLimit());
        //httpSession.setAttribute("event", event);
        model.addAttribute("event", event);
        return "/event/form";
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Long id){
        final Event event = new Event();
        event.setId(id);
        return event;
    }

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<Event> getModelAttributeEvent(@Validated @ModelAttribute("newEvent") Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("=========================");
            bindingResult.getAllErrors().stream()
                    .forEach(System.out::println);
            System.out.println("=========================");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/eventValid")
    public String getEventValid(@Valid @ModelAttribute("newEvent") Event event, BindingResult bindingResult,
                                Model model, SessionStatus sessionStatus){
        if(bindingResult.hasErrors()){
            return "/event/form";
        }

        sessionStatus.setComplete();
        return "redirect:/event/list";
    }

    @GetMapping("/event/list")
    public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime){
        final Event event = new Event();
        event.setName("Spring");
        event.setLimit(10);

        final Event event1 = new Event();
        event1.setName((String)model.getAttribute("name"));
        event1.setLimit((int)model.getAttribute("limit"));

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(event1);
        model.addAttribute(eventList);

        System.out.println("==================================");
        System.out.println(visitTime.toString());
        System.out.println("==================================");

        return "/event/list";
    }

    @PostMapping("/event")
    @ResponseBody
    public Event getPostEvent(@RequestParam String name, @RequestParam int limit){
        final Event event = new Event();
        event.setName(name);
        event.setLimit(limit);
        return event;
    }
}
