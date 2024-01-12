package com.example.demo.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ProductionMyService implements MyService{
    @Override
    public String getMessage() {
        return "Production Service";
    }
}
