package com.hanghae.hanghaecloncodingjeongyookgak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> result = new HashMap<>();

        result.put("result", "success");
        return result;
    }
}
