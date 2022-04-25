package com.example.easyjava.controller;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import org.nibblesec.tools.SerialKiller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public HelloController() {
    }

    @GetMapping({"/hello"})
    public String index() {
        return "hello";
    }

    @PostMapping({"/hello"})
    public String index(@RequestBody String baseStr) throws Exception {
        byte[] decode = Base64.getDecoder().decode(baseStr);
        ObjectInputStream ois = new SerialKiller(new ByteArrayInputStream(decode), "serialkiller.xml");
        ois.readObject();
        return "hello";
    }
}
