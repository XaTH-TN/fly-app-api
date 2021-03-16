package com.edu.fly.controller;

import com.edu.fly.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @Autowired
    private VersionService versionService;
    @PostMapping
    public ResponseEntity<?> getVersion(){

        return ResponseEntity.ok(versionService.getVersion());
    }
}
