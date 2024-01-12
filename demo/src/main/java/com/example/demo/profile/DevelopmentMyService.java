package com.example.demo.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class DevelopmentMyService implements MyService{
    @Override
    public String getMessage() {
        return "Development Service";
    }
}
