package com.popcoder.springbasic.service;

import com.popcoder.springbasic.model.User;
import com.popcoder.springbasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 스프링이 컴포넌트 스캔을 통해 Bean 에 등록을 해줌 ( IoC )
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int register(User user) {
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService : register() : " + e.getMessage());
        }
        return -1;
    }
}
