package com.bhhan.springbootmvc.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hbh5274@gmail.com on 2020-01-26
 * Github : http://github.com/bhhan5274
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fileUploadTest() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile("file", "text.txt", MediaType.TEXT_PLAIN_VALUE, "hello world!!!".getBytes());

        mockMvc.perform(multipart("/file")
                    .file(mockFile))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
    }
}