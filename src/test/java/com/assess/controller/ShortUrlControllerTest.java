package com.assess.controller;

import com.assess.dto.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortUrlController.class)
public class ShortUrlControllerTest {

    @Autowired
    MockMvc mvc;

    @Mock
    Address address;

    @MockBean
    private Map<String, Address> map;

    public static final String LONG_ADDRESS = "https://www.google.com";
    public static final String SHORT_ADDRESS = "https://short.com/xyz123";

    @Test
    public void encodeTest() throws Exception {
        mvc.perform(post("/encode")
                .content(LONG_ADDRESS)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void decodeTest() throws Exception {
        address = new Address(LONG_ADDRESS, LocalDateTime.now());
        Mockito.when(map.get(any())).thenReturn(address);

        mvc.perform(post("/decode")
                .content(SHORT_ADDRESS)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
}