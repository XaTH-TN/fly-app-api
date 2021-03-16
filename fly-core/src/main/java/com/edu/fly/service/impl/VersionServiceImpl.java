package com.edu.fly.service.impl;

import com.edu.fly.service.VersionService;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements VersionService {
    @Override
    public String getVersion() {
        return "0.0.1";
    }
}
