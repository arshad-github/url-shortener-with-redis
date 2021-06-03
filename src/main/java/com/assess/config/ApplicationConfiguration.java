package com.assess.config;

import com.assess.dto.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Map<String, Address> map() {
        final Map<String, Address> map = new HashMap<>();
        return map;
    }

}
