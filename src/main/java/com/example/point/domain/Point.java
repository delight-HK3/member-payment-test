package com.example.point.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

/**
 * point(포인트) Entity 
 */
@Entity
@Getter
@Table(name="point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "결제 일련번호 ID (고유 키)")
    private Long id; 

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    @Comment(value = "회원 ID (외래 키)")
    private Member member;

    @Column(name = "amountpoint")
    @Comment(value = "남은 포인트")
    private int amountpoint;

}
