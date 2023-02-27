package com.popcoder.springbasic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
@Entity // Create table in DB
// @DynamicInsert // insert 시에 null 인 필드 제외시켜준다
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 전략을 따라가겠다
    private Long id;

    @Column(nullable = false, length = 30, unique = true) // length default = 255
    private String username;

    @Column(nullable = false, length = 100) // 해쉬로 암호화된 패스워드를 넣을것이기 때문에 넉넉하게 잡는게 좋음
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'USER'") // " ' USER ' "
    // DB는 RoleType이라는 타입 없기때문에 @Enumerated로 알려줌
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋음 >> USER, ADMIN

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate;
}
