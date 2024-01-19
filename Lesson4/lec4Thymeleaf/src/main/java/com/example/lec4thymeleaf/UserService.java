package com.example.lec4thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        userRepository.save(new User(null, "Evgeniy", "fgdgg@fgdg.fgd"));
        userRepository.save(new User(null, "Ivan", "dhjhgj@fgdg.fgd"));
        userRepository.save(new User(null, "Kate", "poiuyuhk@fgdg.fgd"));
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
}
