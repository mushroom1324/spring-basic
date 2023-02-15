package com.popcoder.springbasic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, One = User => 여러개의 게시글을 한명의 유저가 작성할 수 있다.
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy >> 연관관계의 주인이 아니다 (난 FK 아니에요) DB에 칼럼을 만들지 마세요
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

}

