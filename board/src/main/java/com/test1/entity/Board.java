package com.test1.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@EntityListeners(value= {AuditingEntityListener.class}) // 특정 엔티티를 보면 감지하는 역할
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 건당 많이 들어오면 Long으로 간다.
    // 보통 그게 아니면 Integer로
    // private Integer bno;
    private Integer bno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private String writer;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate", updatable = false)
    private LocalDateTime modDate;

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
