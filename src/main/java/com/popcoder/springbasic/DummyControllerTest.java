package com.popcoder.springbasic;


import com.popcoder.springbasic.model.RoleType;
import com.popcoder.springbasic.model.User;
import com.popcoder.springbasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // DI(Dependency Injection)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "Cannot find user with id: " + id + "\n" + e.getMessage();
        }

        return "User deleted with id: " + id;
    }

    // save 함수는 아이디를 전달하지 않으면 insert
    // 아이디를 전달하고 아이디에 대한 데이터가 있으면 update
    // 아이디를 전달하고 아이디에 대한 데이터가 없으면 insert
    @Transactional // 함수 종료시 자동으로 commit (변경을 감지해서 변경시 업데이트해줌 -> 더티체킹)
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {
        // @RequestBody: JSON 데이터 요청 -> 자바 오브젝트로 바꿔줌(MessageConverter의 Jackson 라이브러리가 해줌)
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다, id: " + id);
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한페이지당 두 건의 데이터를 리턴받아볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 parameter 전달 받을 수 있음
    // http://localhost:8080/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {

        // DB에서 못찾으면 유저가 null이 됨 >> 핸들링 해라.
        // Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해라.
//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다 id : " + id);
//            }
//        });

        // 람다식
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저는 없습니다 id: " + id);
        });

        // 요청 : 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링부트 = MessageConverter가 응답 시 자동 작동
        // 만약 자바 오브젝트르 리턴하게 되면 메세지컨버터가 잭슨 라이브러리 사용하여 제이슨으로 변환 후 던져줌
        return user;

    }

    // http://localhost:8080/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { // key-value 형태 데이터를 받는다 (규칙)
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "Register completed.";

    }
}

