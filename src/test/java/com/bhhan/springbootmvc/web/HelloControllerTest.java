package com.bhhan.springbootmvc.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by hbh5274@gmail.com on 2020-01-25
 * Github : http://github.com/bhhan5274
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEventValid() throws Exception {
        final ResultActions result = mockMvc.perform(post("/eventValid")
                .param("name", "bhhan")
                .param("limit", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());

        final ModelAndView modelAndView = result.andReturn().getModelAndView();
        final Map<String, Object> model = modelAndView.getModel();
        System.out.println(model.size());
    }

    @Test
    public void eventForm() throws Exception {
        mockMvc.perform(get("/event/form"))
                .andDo(print())
                .andExpect(view().name("/event/form"))
                .andExpect(model().attributeExists("limit"))
                .andExpect(request().sessionAttribute("event", notNullValue()));
    }

    @Test
    public void getModelAttributeEvent() throws Exception {
        mockMvc.perform(get("/event")
                .param("name", "bhhan")
                .param("limit", "100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("limit").exists());
    }

    @Test
    public void getModelAttributeEvent_fail() throws Exception {
        mockMvc.perform(get("/event")
                .param("name", "bhhan")
                .param("limit", "100g"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/event/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists());
    }

    @Test
    public void getPostEvent() throws Exception {
        mockMvc.perform(post("/event")
                .param("name", "bhhan")
                .param("limit", "1000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").exists());
    }

    @Test
    public void getEvent_session() throws Exception {
        final Event event = new Event();
        event.setName("Winter is coming.");
        event.setLimit(10000);

        mockMvc.perform(get("/event/list")
                .sessionAttr("visitTime", LocalDateTime.now())
                .flashAttr("name", "bhhan")
                .flashAttr("limit", 1234))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories", "subjects"))
                .andExpect(xpath("//p").nodeCount(2));
    }
}