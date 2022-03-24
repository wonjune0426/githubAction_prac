package com.example.gitaction_test.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    // 테스트용
    @GetMapping("/user/{userId}")
    public String getUserNick(@PathVariable Long userId) {
        return userService.getUseId(userId);
    }

}
