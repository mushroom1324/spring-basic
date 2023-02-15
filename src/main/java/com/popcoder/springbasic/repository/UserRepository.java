package com.popcoder.springbasic.repository;

import com.popcoder.springbasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO(Data Access Object)
// 자동으로 빈으로 등록이 됨
public interface UserRepository extends JpaRepository<User, Long> {
}
