package com.example.point.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

/**
 * payment_log(결제내역) Entity 
 */
@Entity
@Getter
@Table(name="payment_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paymentlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment(value = "결제 일련번호 ID (고유 키)")
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @Comment(value = "회원 ID (외래 키)")
    private Member member;

    @Column(name = "amount")
    @Comment(value = "결제 금액")
    private int amount;

    @Column(name = "payment")
    @Comment(value = "결제 플랫폼")
    private String payment;
    
    @Column(name = "paymenttype")
    @Comment(value = "결제 수단")
    private String paymenttype;

    @Column(name = "code")
    @Comment(value = "결제 상태 코드")
    private int code;

    @Column(name = "message")
    @Comment(value = "결제 상태 메세지")
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment(value = "생성 시간")
    private LocalDateTime createdAt;
    
    @Builder
    public Paymentlog(Member member, int amount, String paymenttype, String payment, 
                        int code, String message, LocalDateTime createdAt){
        this.member = member;
        this.amount = amount;
        this.paymenttype = paymenttype;
        this.payment = payment;
        this.code = code;
        this.message = message;
        this.createdAt = createdAt;
    }

}
