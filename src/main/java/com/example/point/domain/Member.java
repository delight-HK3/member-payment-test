package com.example.point.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.point.model.member.MemberResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
    @ColumnDefault(value = "0")
    @Comment(value = "회원 조회수")
    private int viewcount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;

    @Version
    @ColumnDefault(value = "0")
    private Long version;

    public MemberResponseDTO memberEntityToDTO(){
        return MemberResponseDTO.builder()
                                .id(id)
                                .name(name)
                                .viewCount(viewcount)
                                .createDt(createdAt)
                                .build();
    }

    public void plusViewCount(){
        this.viewcount++;   // 조회수 증가
        this.version++;     // 버전 증가
    }
}
