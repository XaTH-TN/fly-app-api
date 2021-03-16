package com.edu.fly.util.impl;

import com.edu.fly.util.UUIDGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.fasterxml.uuid.Generators;

@Component
public class UUIDGeneratorImpl implements UUIDGenerator {

    private TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator();

    @Override
    public String generateUUID() {
        return uuidGenerator.generate().toString();
    }
}
