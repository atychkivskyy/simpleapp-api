package com.atychkivskyy.simpleapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/info")
public class InfoController {

    @Value("${app.version:unknown}")
    private String appVersion;

    @Value("${app.environment:unkown}")
    private String appEnvironment;

    @GetMapping
    public ResponseEntity<Map<String, String>> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("application", "simpleapp-api");
        info.put("version", appVersion);
        info.put("environment", appEnvironment);
        return ResponseEntity.ok(info);
    }
}
