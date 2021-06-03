package com.assess.controller;

import com.assess.dto.Address;
import com.assess.dto.ResponseAddress;
import com.assess.error.ShortUrlError;
import com.assess.util.Hash;
import com.assess.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class ShortUrlController {

    @Autowired
    private Map<String, Address> map;

    @Value("${base.url}")
    private String BASE;

    @RequestMapping(value="/encode", method=RequestMethod.POST)
    public ResponseEntity encode(@RequestBody final String input) throws URISyntaxException {
        if (Validator.urlInvalid(input)) return ResponseEntity.badRequest().body(new ShortUrlError("Invalid input url"));
        URI uri = new URI(input);

        final Address address = Address.createShort(input);
        final String key = Hash.getHash(address.getOriginalUrl());
        map.putIfAbsent(key, address);
        return ResponseEntity.ok(new ResponseAddress(uri.getScheme()+BASE+key));
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

        final Address address = map.get(inputArr[inputArr.length-1]);

        return (address == null) ?
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShortUrlError("No matching value")) :
        ResponseEntity.ok(new ResponseAddress(address.getOriginalUrl()));
    }

    private boolean invalidShortUrl(String s) {
        return !s.equals("short.com");
    }

}
