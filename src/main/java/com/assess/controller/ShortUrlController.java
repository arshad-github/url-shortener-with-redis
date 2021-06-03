package com.assess.controller;

import com.assess.dto.Address;
import com.assess.dto.ResponseAddress;
import com.assess.error.ShortUrlError;
import com.assess.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@RestController
public class ShortUrlController {

    @Autowired
    private RedisTemplate<String, Address> redis;

    @Value("${base.url}")
    private String BASE;

    private static final long TTL = 86400;

    @RequestMapping(value="/encode", method=RequestMethod.POST)
    public ResponseEntity encode(@RequestBody final String input) throws URISyntaxException {
        if (Validator.urlInvalid(input)) return ResponseEntity.badRequest().body(new ShortUrlError("Invalid input url"));
        URI uri = new URI(input);

        final Address address = Address.createShort(input);
        redis.opsForValue().set(address.getShortId(), address, TTL, TimeUnit.SECONDS);
        return ResponseEntity.ok(new ResponseAddress(uri.getScheme()+BASE+address.getShortId()));
    }

    @RequestMapping(value="/decode", method=RequestMethod.POST)
    public ResponseEntity decode(@RequestBody final String input) throws URISyntaxException {
        if (Validator.urlInvalid(input)) {
            return ResponseEntity.badRequest().body(new ShortUrlError("Invalid input url"));
        }

        String[] inputArr = input.split("/");
        if (invalidShortUrl(inputArr[inputArr.length-2])) {
            return ResponseEntity.badRequest().body(new ShortUrlError("Invalid short.com address"));
        }

        final Address address = redis.opsForValue().get(inputArr[inputArr.length-1]);

        return (address == null) ?
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShortUrlError("No matching value")) :
        ResponseEntity.ok(new ResponseAddress(address.getOriginalUrl()));
    }

    private boolean invalidShortUrl(String s) {
        return !s.equals("short.com");
    }

}
