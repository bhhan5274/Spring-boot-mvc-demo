package com.bhhan.springbootmvc.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by hbh5274@gmail.com on 2020-01-23
 * Github : http://github.com/bhhan5274
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest
class SampleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        this.mockMvc.perform(get("/hello2")
                .header(HttpHeaders.AUTHORIZATION, "localhost")
                .param("name", "bhhan"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void helloGetTest() throws Exception {
        mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void helloPostTest() throws Exception {
        mockMvc.perform(post("/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}