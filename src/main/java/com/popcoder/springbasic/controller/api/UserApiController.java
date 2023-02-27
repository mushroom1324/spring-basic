package com.popcoder.springbasic.controller.api;

import com.popcoder.springbasic.dto.ResponseDto;
import com.popcoder.springbasic.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save called.");
        // 실제로 DB 에 인서트 하고 아래에서 리턴이 되면 됨.
        return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바오브젝트를 JSON 으로 변환해서 리턴 (Jackson)
    }
}
