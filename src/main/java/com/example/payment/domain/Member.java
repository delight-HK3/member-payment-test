package com.example.payment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

/**
 * member(회원) Entity 
 */
@Entity
@Getter
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "회원 ID (고유 키)")
    private Long id; 

    @Column(name = "name", length = 255, nullable = false)
    @Comment(value = "회원 이름")
    private String name;
    
    @Column(name = "viewcount", nullable = false)
    @Comment(value = "회원 조회수")
    private int viewcount = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;
}
